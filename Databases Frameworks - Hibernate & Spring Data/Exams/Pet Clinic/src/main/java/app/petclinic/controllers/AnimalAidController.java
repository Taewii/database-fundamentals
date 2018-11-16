package app.petclinic.controllers;

import app.petclinic.domain.dto.binding.json.AnimalAidJSONImportDTO;
import app.petclinic.parser.interfaces.Parser;
import app.petclinic.services.animalaid.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser jsonParser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService,
                               @Qualifier("JSONParser") Parser jsonParser) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        AnimalAidJSONImportDTO[] animalAids = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);

        StringBuilder sb = new StringBuilder();
        for (AnimalAidJSONImportDTO animalAid : animalAids) {
            sb.append(this.animalAidService.create(animalAid)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
