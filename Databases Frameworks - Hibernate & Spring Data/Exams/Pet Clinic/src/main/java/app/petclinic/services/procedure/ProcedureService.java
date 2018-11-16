package app.petclinic.services.procedure;

import app.petclinic.domain.dto.binding.xml.ProcedureXMLImportDTO;
import app.petclinic.domain.dto.view.ProcedureWrapperXMLExportDTO;

public interface ProcedureService {

    String create(ProcedureXMLImportDTO procedureDto);

    ProcedureWrapperXMLExportDTO getAllProcedures();
}