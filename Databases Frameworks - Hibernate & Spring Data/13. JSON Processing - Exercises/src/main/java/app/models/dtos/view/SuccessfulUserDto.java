package app.models.dtos.view;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SuccessfulUserDto implements Serializable {

    private String firstName;
    private String lastName;
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
