package org.softuni.mostwanted.models.dtos.binding.json;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DistrictJsonImportDTO implements Serializable {

    @NotNull
    private String name;
    private String townName;

    public DistrictJsonImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
