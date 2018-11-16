package app.petclinic.domain.dto.binding.xml;

import app.petclinic.parser.XMLParser;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO implements Serializable {

    @XmlElement
    @Length(min = 3, max = 40)
    private String vet;

    @XmlElement
    @Pattern(regexp = "^.{7}\\d{3}$")
    private String animal;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    @Valid
    private ProcedureAnimalAidXMLImportDTO[] animalAids;

    @XmlElement
    @XmlJavaTypeAdapter(XMLParser.DateTimeAdapter.class)
    private Date date;

    public ProcedureXMLImportDTO() {
    }

    public String getVet() {
        return this.vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public ProcedureAnimalAidXMLImportDTO[] getAnimalAids() {
        return this.animalAids;
    }

    public void setAnimalAids(ProcedureAnimalAidXMLImportDTO[] animalAids) {
        this.animalAids = animalAids;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
