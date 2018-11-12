package org.softuni.mostwanted.models.dtos.binding.xml.wrappers;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceEntryXmlImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryWrapper implements Serializable {

    @XmlElement(name = "race-entry")
    private RaceEntryXmlImportDTO[] raceEntries;

    public RaceEntryWrapper() {
    }

    public RaceEntryXmlImportDTO[] getRaceEntries() {
        return this.raceEntries;
    }
}
