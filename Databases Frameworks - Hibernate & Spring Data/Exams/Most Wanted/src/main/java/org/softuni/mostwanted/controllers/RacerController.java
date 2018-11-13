package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.json.RacerJsonImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.racer.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {

    private final RacerService racerService;
    private final Parser jsonParser;

    @Autowired
    public RacerController(RacerService racerService,
                           Parser jsonParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String content) throws IOException, JAXBException {
        RacerJsonImportDTO[] racers = this.jsonParser.read(RacerJsonImportDTO[].class, content);

        StringBuilder sb = new StringBuilder();
        for (RacerJsonImportDTO racer : racers) {
            sb.append(this.racerService.create(racer)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportRacersWithCars() throws IOException, JAXBException {
        return this.jsonParser.write(this.racerService.racersWithAtleastOneCar());
    }

    public String exportRacerTownsAsJson() throws IOException, JAXBException {
        return this.jsonParser.write(this.racerService.racingTownsWithRacersCount());
    }
}
