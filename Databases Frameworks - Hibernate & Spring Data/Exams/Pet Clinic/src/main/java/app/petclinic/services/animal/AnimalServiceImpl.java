package app.petclinic.services.animal;

import app.petclinic.Config;
import app.petclinic.domain.dto.binding.json.AnimalJSONImportDTO;
import app.petclinic.domain.dto.view.AnimalsJSONExportDTO;
import app.petclinic.domain.models.Animal;
import app.petclinic.domain.models.Passport;
import app.petclinic.parser.ValidationUtil;
import app.petclinic.parser.interfaces.ModelParser;
import app.petclinic.repositories.AnimalRepository;
import app.petclinic.services.passport.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final PassportService passportService;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository,
                             PassportService passportService,
                             ModelParser modelParser,
                             ValidationUtil validation) {
        this.animalRepository = animalRepository;
        this.passportService = passportService;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(AnimalJSONImportDTO animalDto) {
        if (!this.validation.isValid(animalDto)) {
            return Config.INVALID_DATA_MESSAGE;
        }

        Passport passport = this.passportService.findOneBySerialNumber(animalDto.getPassport().getSerialNumber());
        if (passport != null) {
            return Config.INVALID_DATA_MESSAGE;
        }

        Animal animal = this.modelParser.convert(animalDto, Animal.class);
        animal.getPassport().setAnimal(animal);
        this.animalRepository.saveAndFlush(animal);
        return String.format(Config.ANIMAL_RECORD_MESSAGE, animal.getName(), animal.getPassport().getSerialNumber());
    }

    @Override
    public List<AnimalsJSONExportDTO> animalsByOwnerPhoneNumber(String phoneNumber) {
        return this.animalRepository.animalsByOwnerPhoneNumber(phoneNumber).stream()
                .map(a -> new AnimalsJSONExportDTO(
                        a.getPassport().getOwnerName(),
                        a.getName(),
                        a.getAge(),
                        a.getPassport().getSerialNumber(),
                        a.getPassport().getRegistrationDate()))
                .collect(Collectors.toList());
    }
}