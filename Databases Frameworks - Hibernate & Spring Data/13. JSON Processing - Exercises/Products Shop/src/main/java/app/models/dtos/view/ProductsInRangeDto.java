package app.models.dtos.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductsInRangeDto implements Serializable {

    private String name;
    private BigDecimal price;
    private String sellerName;

    public ProductsInRangeDto() {
    }

    public ProductsInRangeDto(String name, BigDecimal price, String sellerName) {
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
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

    public String getSellerName() {
        return this.sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
