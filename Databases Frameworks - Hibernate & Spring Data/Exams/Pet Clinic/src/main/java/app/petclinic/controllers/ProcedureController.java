package app.petclinic.controllers;

import app.petclinic.domain.dto.binding.xml.ProcedureWrapperXMLImportDTO;
import app.petclinic.domain.dto.binding.xml.ProcedureXMLImportDTO;
import app.petclinic.parser.interfaces.Parser;
import app.petclinic.services.procedure.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final Parser xmlParser;

    @Autowired
    public ProcedureController(ProcedureService procedureService,
                               @Qualifier("XMLParser") Parser xmlParser) {
        this.procedureService = procedureService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        ProcedureWrapperXMLImportDTO wrapper = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);

        StringBuilder sb = new StringBuilder();
        for (ProcedureXMLImportDTO procedure : wrapper.getProcedures()) {
            sb.append(this.procedureService.create(procedure)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportProcedures() {
        return this.xmlParser.write(this.procedureService.getAllProcedures());
    }
}
