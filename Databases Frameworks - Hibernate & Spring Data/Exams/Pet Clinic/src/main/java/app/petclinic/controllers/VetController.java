package app.petclinic.controllers;

import app.petclinic.domain.dto.binding.xml.VetWrapperXMLImportDTO;
import app.petclinic.domain.dto.binding.xml.VetXMLImportDTO;
import app.petclinic.parser.interfaces.Parser;
import app.petclinic.services.vet.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class VetController {

    private final VetService vetService;
    private final Parser xmlParser;

    @Autowired
    public VetController(VetService vetService,
                         @Qualifier("XMLParser") Parser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        VetWrapperXMLImportDTO wrapper = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);

        StringBuilder sb = new StringBuilder();
        for (VetXMLImportDTO vet : wrapper.getVets()) {
            sb.append(this.vetService.create(vet)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
