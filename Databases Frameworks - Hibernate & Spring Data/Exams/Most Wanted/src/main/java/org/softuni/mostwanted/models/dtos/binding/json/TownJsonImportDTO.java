package org.softuni.mostwanted.models.dtos.binding.json;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TownJsonImportDTO implements Serializable {

    private String name;

    public TownJsonImportDTO() {
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
