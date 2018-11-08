package app.models.dtos.view.wrappers;

import app.models.dtos.view.products.ProductsInRangeDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductsInRangeWrapper {

    @XmlElement(name = "product")
    private List<ProductsInRangeDto> products;

    public ProductsInRangeWrapper() {
    }

    public ProductsInRangeWrapper(List<ProductsInRangeDto> products) {
        this.products = products;
    }
}
