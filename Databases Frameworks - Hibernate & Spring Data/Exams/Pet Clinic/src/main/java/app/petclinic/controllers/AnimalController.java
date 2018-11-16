package app.petclinic.controllers;

import app.petclinic.domain.dto.binding.json.AnimalJSONImportDTO;
import app.petclinic.parser.interfaces.Parser;
import app.petclinic.services.animal.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final Parser jsonParser;

    @Autowired
    public AnimalController(AnimalService animalService,
                            @Qualifier("JSONParser") Parser jsonParser) {
        this.animalService = animalService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        AnimalJSONImportDTO[] animals = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);

        StringBuilder sb = new StringBuilder();
        for (AnimalJSONImportDTO animal : animals) {
            sb.append(this.animalService.create(animal)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        return this.jsonParser.write(this.animalService.animalsByOwnerPhoneNumber(phoneNumber));
    }
}
