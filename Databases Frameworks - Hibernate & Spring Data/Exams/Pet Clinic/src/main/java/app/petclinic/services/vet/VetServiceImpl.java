package app.petclinic.services.vet;

import app.petclinic.Config;
import app.petclinic.domain.dto.binding.xml.VetXMLImportDTO;
import app.petclinic.domain.models.Vet;
import app.petclinic.parser.ValidationUtil;
import app.petclinic.parser.interfaces.ModelParser;
import app.petclinic.repositories.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository,
                          ModelParser modelParser,
                          ValidationUtil validation) {
        this.vetRepository = vetRepository;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(VetXMLImportDTO vetDto) {
        if (!this.validation.isValid(vetDto)) {
            return Config.INVALID_DATA_MESSAGE;
        }

        if (this.findOneByName(vetDto.getName()) != null) {
            return Config.INVALID_DATA_MESSAGE;
        }

        Vet vet = this.modelParser.convert(vetDto, Vet.class);
        this.vetRepository.saveAndFlush(vet);

        return String.format(Config.VALID_RECORD_MESSAGE, vet.getName());
    }

    @Override
    public Vet findOneByName(String name) {
        return this.vetRepository.findOneByName(name);
    }
}