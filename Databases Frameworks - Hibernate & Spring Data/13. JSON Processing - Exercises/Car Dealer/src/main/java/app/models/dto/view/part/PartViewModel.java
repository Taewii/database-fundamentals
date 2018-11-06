package app.models.dto.view.part;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartViewModel implements Serializable {

    private String name;
    private BigDecimal price;

    public PartViewModel() {
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
