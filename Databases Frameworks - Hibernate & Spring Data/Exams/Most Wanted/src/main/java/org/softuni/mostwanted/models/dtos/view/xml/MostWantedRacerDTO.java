package org.softuni.mostwanted.models.dtos.view.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MostWantedRacerDTO implements Serializable {

    @XmlElement(name = "racer")
    private RacerXmlExportDTO racer;

    public MostWantedRacerDTO() {
    }

    public RacerXmlExportDTO getRacer() {
        return this.racer;
    }

    public void setRacer(RacerXmlExportDTO racer) {
        this.racer = racer;
    }
}
