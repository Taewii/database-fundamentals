package org.softuni.ruk.models.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BranchJsonImportDTO implements Serializable {

    @NotNull
    private String name;

    public BranchJsonImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
