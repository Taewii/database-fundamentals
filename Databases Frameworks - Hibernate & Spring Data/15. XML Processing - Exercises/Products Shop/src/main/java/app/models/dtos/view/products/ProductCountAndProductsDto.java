package app.models.dtos.view.products;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductCountAndProductsDto implements Serializable {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "product")
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
        this.count = products.size();
    }
}
