package app.models.dto.binding.wrappers;

import app.models.dto.binding.PartDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartsWrapper implements Serializable {

    @XmlElement(name = "part")
    private PartDto[] parts;

    public PartsWrapper() {
    }

    public PartDto[] getParts() {
        return this.parts;
    }
}
