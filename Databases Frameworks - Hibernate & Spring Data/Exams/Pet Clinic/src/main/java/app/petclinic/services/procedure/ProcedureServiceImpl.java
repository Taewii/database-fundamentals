package app.petclinic.services.procedure;

import app.petclinic.Config;
import app.petclinic.domain.dto.binding.xml.ProcedureAnimalAidXMLImportDTO;
import app.petclinic.domain.dto.binding.xml.ProcedureXMLImportDTO;
import app.petclinic.domain.dto.view.ProcedureAnimalAidXMLExportDTO;
import app.petclinic.domain.dto.view.ProcedureWrapperXMLExportDTO;
import app.petclinic.domain.dto.view.ProcedureXMLExportDTO;
import app.petclinic.domain.models.Animal;
import app.petclinic.domain.models.AnimalAid;
import app.petclinic.domain.models.Procedure;
import app.petclinic.domain.models.Vet;
import app.petclinic.parser.ValidationUtil;
import app.petclinic.repositories.ProcedureRepository;
import app.petclinic.services.animalaid.AnimalAidService;
import app.petclinic.services.passport.PassportService;
import app.petclinic.services.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final PassportService passportService;
    private final VetService vetService;
    private final AnimalAidService animalAidService;
    private final ValidationUtil validation;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository,
                                PassportService passportService,
                                VetService vetService,
                                AnimalAidService animalAidService,
                                ValidationUtil validation) {
        this.procedureRepository = procedureRepository;
        this.passportService = passportService;
        this.vetService = vetService;
        this.animalAidService = animalAidService;
        this.validation = validation;
    }

    @Override
    public String create(ProcedureXMLImportDTO procedureDto) {
        if (!this.validation.isValid(procedureDto)) {
            return Config.INVALID_DATA_MESSAGE;
        }

        Vet vet = this.vetService.findOneByName(procedureDto.getVet());
        Animal animal = this.passportService.findOneBySerialNumber(procedureDto.getAnimal()).getAnimal();

        if (vet == null || animal == null) {
            return Config.INVALID_DATA_MESSAGE;
        }

        List<AnimalAid> animalAids = new ArrayList<>();
        for (ProcedureAnimalAidXMLImportDTO animalAid : procedureDto.getAnimalAids()) {
            AnimalAid aid = this.animalAidService.findOneByName(animalAid.getName());
            if (aid != null) {
                List<AnimalAid> collect = animalAids.stream().filter(a -> a.getName()
                        .equals(animalAid.getName())).collect(Collectors.toList());
                if (collect.isEmpty()) {
                    animalAids.add(aid);
                }
            } else {
                return Config.INVALID_DATA_MESSAGE;
            }
        }

        Procedure procedure = new Procedure();
        procedure.setAnimal(animal);
        procedure.setVet(vet);
        procedure.setServices(animalAids);
        procedure.setDate(procedureDto.getDate());

        this.procedureRepository.saveAndFlush(procedure);
        procedure.getServices().forEach(s -> s.getProcedures().add(procedure));
        return String.format(Config.VALID_RECORD_MESSAGE, "");
    }

    @Override
    public ProcedureWrapperXMLExportDTO getAllProcedures() {
        List<Procedure> all = this.procedureRepository.findAll();

        List<ProcedureXMLExportDTO> dtos = new ArrayList<>();
        for (Procedure procedure : all) {
            ProcedureXMLExportDTO procedureDto = new ProcedureXMLExportDTO();
            procedureDto.setOwner(procedure.getAnimal().getPassport().getOwnerPhoneNumber());
            procedureDto.setAnimalPassport(procedure.getAnimal().getPassport().getSerialNumber());
            procedureDto.setAnimalAids(procedure.getServices().stream()
                    .map(aid -> new ProcedureAnimalAidXMLExportDTO(aid.getName(), aid.getPrice())).collect(Collectors.toList()));

            dtos.add(procedureDto);
        }

        ProcedureWrapperXMLExportDTO wrapper = new ProcedureWrapperXMLExportDTO();
        wrapper.setProcedures(dtos);

        return wrapper;
    }
}