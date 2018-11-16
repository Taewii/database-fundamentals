package app.petclinic.domain.dto.binding.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureWrapperXMLImportDTO implements Serializable {

    @XmlElement(name = "procedure")
    private ProcedureXMLImportDTO[] procedures;

    public ProcedureWrapperXMLImportDTO() {
    }

    public ProcedureXMLImportDTO[] getProcedures() {
        return this.procedures;
    }

    public void setProcedures(ProcedureXMLImportDTO[] procedures) {
        this.procedures = procedures;
    }
}
