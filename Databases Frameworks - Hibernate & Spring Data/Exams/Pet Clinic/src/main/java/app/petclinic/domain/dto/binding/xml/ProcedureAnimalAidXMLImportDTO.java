package app.petclinic.domain.dto.binding.xml;

import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLImportDTO implements Serializable {

    @XmlElement
    @Length(min = 3)
    private String name;

    public ProcedureAnimalAidXMLImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
