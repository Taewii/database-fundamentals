package org.softuni.mostwanted.models.dtos.binding.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceXmlImportDTO implements Serializable {

    @NotNull
    @XmlElement
    private Integer laps;

    @NotNull
    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RaceEntryDTO> entries;

    public RaceXmlImportDTO() {
    }

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<RaceEntryDTO> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntryDTO> entries) {
        this.entries = entries;
    }
}
