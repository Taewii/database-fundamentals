package org.softuni.mostwanted.models.dtos.view.xml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RacerXmlExportDTO implements Serializable {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryXmlExportDTO> entries;

    public RacerXmlExportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntryXmlExportDTO> getEntries() {
        return this.entries;
    }

    public void setEntries(List<EntryXmlExportDTO> entries) {
        this.entries = entries;
    }
}
