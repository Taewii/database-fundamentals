package app.models.dtos.view.users;

import app.models.dtos.view.products.ProductWithSellerNamesDto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SuccessfulUserDto implements Serializable {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<ProductWithSellerNamesDto> productsSold;

    public SuccessfulUserDto() {
        this.productsSold = new HashSet<>();
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

    public Set<ProductWithSellerNamesDto> getProductsSold() {
        return this.productsSold;
    }

    public void setProductsSold(Set<ProductWithSellerNamesDto> productsSold) {
        this.productsSold = productsSold;
    }
}
