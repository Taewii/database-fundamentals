package org.softuni.mostwanted.models.dtos.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "entry")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryDTO implements Serializable {

    @XmlAttribute
    private Integer id;

    public RaceEntryDTO() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
