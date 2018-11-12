package org.softuni.mostwanted.models.dtos.binding.xml.wrappers;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceXmlImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "races")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceWrapper implements Serializable {

    @XmlElement(name = "race")
    private RaceXmlImportDTO[] races;

    public RaceWrapper() {
    }

    public RaceXmlImportDTO[] getRaces() {
        return this.races;
    }

    public void setRaces(RaceXmlImportDTO[] races) {
        this.races = races;
    }
}
