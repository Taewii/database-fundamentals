package app.petclinic.domain.dto.binding.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO implements Serializable {

    @XmlElement(name = "vet")
    private VetXMLImportDTO[] vets;

    public VetWrapperXMLImportDTO() {
    }

    public VetXMLImportDTO[] getVets() {
        return this.vets;
    }

    public void setVets(VetXMLImportDTO[] vets) {
        this.vets = vets;
    }
}
