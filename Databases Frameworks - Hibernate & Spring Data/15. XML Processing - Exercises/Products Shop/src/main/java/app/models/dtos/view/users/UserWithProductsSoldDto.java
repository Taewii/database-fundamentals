package app.models.dtos.view.users;

import app.models.dtos.view.products.ProductCountAndProductsDto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserWithProductsSoldDto implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "sold-products")
    private ProductCountAndProductsDto productsSold;

    public UserWithProductsSoldDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public ProductCountAndProductsDto getProductsSold() {
        return this.productsSold;
    }

    public void setProductsSold(ProductCountAndProductsDto productsSold) {
        this.productsSold = productsSold;
    }
}
