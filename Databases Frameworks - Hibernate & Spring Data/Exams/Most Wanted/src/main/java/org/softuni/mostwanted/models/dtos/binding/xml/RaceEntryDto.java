package org.softuni.mostwanted.models.dtos.binding.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "entry")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryDto implements Serializable {

    @XmlAttribute
    private Integer id;

    public RaceEntryDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
