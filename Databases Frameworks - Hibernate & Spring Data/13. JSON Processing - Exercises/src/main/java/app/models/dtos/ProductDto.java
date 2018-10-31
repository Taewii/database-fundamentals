package app.models.dtos;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDto implements Serializable {

    private String name;
    private BigDecimal price;

    public ProductDto() {
    }

    @Size(min = 3)
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
