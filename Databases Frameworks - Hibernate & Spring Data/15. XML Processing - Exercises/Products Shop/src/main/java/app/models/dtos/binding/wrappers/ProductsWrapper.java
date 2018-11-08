package app.models.dtos.binding.wrappers;

import app.models.dtos.binding.ProductDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductsWrapper {

    @XmlElement(name = "product")
    private ProductDto[] products;

    public ProductsWrapper() {
    }

    public ProductDto[] getProducts() {
        return this.products;
    }
}
