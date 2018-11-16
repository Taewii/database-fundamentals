package app.petclinic.services.animalaid;

import app.petclinic.Config;
import app.petclinic.domain.dto.binding.json.AnimalAidJSONImportDTO;
import app.petclinic.domain.models.AnimalAid;
import app.petclinic.parser.ValidationUtil;
import app.petclinic.parser.interfaces.ModelParser;
import app.petclinic.repositories.AnimalAidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalaidRepository;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalaidRepository,
                                ModelParser modelParser,
                                ValidationUtil validation) {
        this.animalaidRepository = animalaidRepository;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(AnimalAidJSONImportDTO animalAidDto) {
        if (!this.validation.isValid(animalAidDto)) {
            return Config.INVALID_DATA_MESSAGE;
        }

        AnimalAid animalAid = this.findOneByName(animalAidDto.getName());

        if (animalAid == null) {
            animalAid = this.modelParser.convert(animalAidDto, AnimalAid.class);
        } else {
            if (!animalAid.getPrice().equals(animalAidDto.getPrice())) {
                animalAid.setPrice(animalAid.getPrice());
            }
        }

        this.animalaidRepository.saveAndFlush(animalAid);
        return String.format(Config.VALID_RECORD_MESSAGE, animalAid.getName());
    }

    @Override
    public AnimalAid findOneByName(String name) {
        return this.animalaidRepository.findOneByName(name);
    }
}