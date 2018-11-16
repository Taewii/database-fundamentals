package app.petclinic.domain.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureWrapperXMLExportDTO implements Serializable {

    @XmlElement(name = "procedure")
    private List<ProcedureXMLExportDTO> procedures;

    public ProcedureWrapperXMLExportDTO() {
    }

    public List<ProcedureXMLExportDTO> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }
}
