package app.petclinic.services.animalaid;

import app.petclinic.domain.dto.binding.json.AnimalAidJSONImportDTO;
import app.petclinic.domain.models.AnimalAid;

public interface AnimalAidService {

    String create(AnimalAidJSONImportDTO animalAidDto);

    AnimalAid findOneByName(String name);
}