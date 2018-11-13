package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceXmlImportDTO;
import org.softuni.mostwanted.models.dtos.binding.xml.wrappers.RaceWrapper;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.race.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {

    private final RaceService raceService;
    private final Parser xmlParser;

    @Autowired
    public RaceController(RaceService raceService, Parser xmlParser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXml(String content) throws IOException, JAXBException {
        RaceWrapper wrapper = this.xmlParser.read(RaceWrapper.class, content);
        RaceXmlImportDTO[] races = wrapper.getRaces();

        StringBuilder sb = new StringBuilder();
        for (RaceXmlImportDTO race : races) {
            sb.append(this.raceService.create(race)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
