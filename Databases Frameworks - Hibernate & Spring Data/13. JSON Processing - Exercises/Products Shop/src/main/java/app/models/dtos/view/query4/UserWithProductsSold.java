package app.models.dtos.view.query4;

import java.io.Serializable;

public class UserWithProductsSold implements Serializable {

    private String firstName;
    private String lastName;
    private Integer age;
    private ProductCountAndProductsDto productsSold;

    public UserWithProductsSold() {
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
