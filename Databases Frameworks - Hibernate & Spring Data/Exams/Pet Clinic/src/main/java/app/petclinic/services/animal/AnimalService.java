package app.petclinic.services.animal;

import app.petclinic.domain.dto.binding.json.AnimalJSONImportDTO;
import app.petclinic.domain.dto.view.AnimalsJSONExportDTO;

import java.util.List;

public interface AnimalService {

    String create(AnimalJSONImportDTO animalDto);

    List<AnimalsJSONExportDTO> animalsByOwnerPhoneNumber(String phoneNumber);
}