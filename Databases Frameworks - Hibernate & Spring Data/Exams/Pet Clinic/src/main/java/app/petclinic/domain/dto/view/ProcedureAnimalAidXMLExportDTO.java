package app.petclinic.domain.dto.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLExportDTO implements Serializable {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    public ProcedureAnimalAidXMLExportDTO() {
    }

    public ProcedureAnimalAidXMLExportDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
