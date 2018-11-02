package app.models.dto.binding;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartDto implements Serializable {

    private String name;
    private BigDecimal price;
    private Long quantity;

    public PartDto() {
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

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
