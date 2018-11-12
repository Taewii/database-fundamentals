package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceEntryXmlImportDTO;
import org.softuni.mostwanted.models.dtos.binding.xml.wrappers.RaceEntryWrapper;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.raceEntry.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {

    private final RaceEntryService raceEntryService;
    private final Parser xmlParser;

    @Autowired
    public RaceEntryController(RaceEntryService raceEntryService,
                               Parser xmlParser) {
        this.raceEntryService = raceEntryService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXml(String content) throws IOException, JAXBException {
        RaceEntryWrapper wrapper = this.xmlParser.read(RaceEntryWrapper.class, content);
        RaceEntryXmlImportDTO[] races = wrapper.getRaceEntries();

        StringBuilder sb = new StringBuilder();
        for (RaceEntryXmlImportDTO race : races) {
            sb.append(this.raceEntryService.create(race)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportMostWantedRacer() throws IOException, JAXBException {
        return this.xmlParser.write(this.raceEntryService.exportMostWantedRacer());
    }
}
