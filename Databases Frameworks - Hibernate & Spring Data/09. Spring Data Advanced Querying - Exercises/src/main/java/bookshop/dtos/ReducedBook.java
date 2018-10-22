package bookshop.dtos;

import java.math.BigDecimal;

public class ReducedBook {

    private String title;
    private String editionType;
    private String ageRegistration;
    private BigDecimal price;

    public ReducedBook(String title, String editionType, String ageRegistration, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRegistration = ageRegistration;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditionType() {
        return this.editionType;
    }

    public void setEditionType(String editionType) {
        this.editionType = editionType;
    }

    public String getAgeRegistration() {
        return this.ageRegistration;
    }

    public void setAgeRegistration(String ageRegistration) {
        this.ageRegistration = ageRegistration;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
