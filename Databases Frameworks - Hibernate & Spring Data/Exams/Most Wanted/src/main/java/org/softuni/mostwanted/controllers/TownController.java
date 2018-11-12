package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.json.TownJsonImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.town.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {

    private final TownService townService;
    private final Parser jsonParser;

    @Autowired
    public TownController(TownService townService,
                          Parser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String content) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        TownJsonImportDTO[] towns = this.jsonParser.read(TownJsonImportDTO[].class, content);

        for (TownJsonImportDTO town : towns) {
            sb.append(this.townService.create(town)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportRacerTownsAsJson() throws IOException, JAXBException {
        return this.jsonParser.write(this.townService);
    }
}
