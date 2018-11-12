package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.json.RacerJsonImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.racer.RacerService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {

    private final RacerService racerService;
    private final Parser jsonParser;

    public RacerController(RacerService racerService,
                           Parser jsonParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String content) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RacerJsonImportDTO[] racers = this.jsonParser.read(RacerJsonImportDTO[].class, content);

        for (RacerJsonImportDTO racer : racers) {
            sb.append(this.racerService.create(racer)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportRacersWithCars() throws IOException, JAXBException {
        return this.jsonParser.write(this.racerService.racersWithAtleastOneCar());
    }
}
