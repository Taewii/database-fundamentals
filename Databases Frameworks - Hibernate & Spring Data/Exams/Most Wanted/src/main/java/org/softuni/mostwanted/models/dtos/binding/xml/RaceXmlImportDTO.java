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
    private List<RaceEntryDto> entries;

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

    public List<RaceEntryDto> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntryDto> entries) {
        this.entries = entries;
    }
}
