package app.models.dtos.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoryDto implements Serializable {

    private String name;

    public CategoryDto() {
    }

    @NotNull
    @Size(min = 3, max = 15)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
