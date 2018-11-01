package app.models.dtos.view.query4;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProductCountAndProductsDto implements Serializable {

    private int count;
    private Set<ProductNameAndPriceDto> products;

    public ProductCountAndProductsDto() {
        this.products = new HashSet<>();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductNameAndPriceDto> getProducts() {
        return this.products;
    }

    public void setProducts(Set<ProductNameAndPriceDto> products) {
        this.products = products;
    }
}
