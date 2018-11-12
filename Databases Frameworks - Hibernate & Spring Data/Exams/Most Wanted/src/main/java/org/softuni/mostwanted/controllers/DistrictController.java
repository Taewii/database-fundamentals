package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.json.DistrictJsonImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.district.DistrictService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private final DistrictService districtService;
    private final Parser jsonParser;

    public DistrictController(DistrictService districtService,
                              Parser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String content) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        DistrictJsonImportDTO[] districts = this.jsonParser.read(DistrictJsonImportDTO[].class, content);

        for (DistrictJsonImportDTO district : districts) {
            sb.append(this.districtService.create(district)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
