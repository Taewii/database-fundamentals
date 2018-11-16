package app.petclinic.domain.dto.binding.json;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;

public class AnimalAidJSONImportDTO implements Serializable {

    @Length(min = 3)
    private String name;

    @DecimalMin("1")
    private BigDecimal price;

    public AnimalAidJSONImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
