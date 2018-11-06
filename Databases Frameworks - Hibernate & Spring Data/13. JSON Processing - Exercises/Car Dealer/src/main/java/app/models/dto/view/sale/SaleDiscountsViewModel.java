package app.models.dto.view.sale;

import app.models.dto.view.car.CarViewModelWithoutPartsAndId;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleDiscountsViewModel implements Serializable {

    private CarViewModelWithoutPartsAndId car;
    private String customerName;
    private Double discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public SaleDiscountsViewModel() {
    }

    public CarViewModelWithoutPartsAndId getCar() {
        return this.car;
    }

    public void setCar(CarViewModelWithoutPartsAndId car) {
        this.car = car;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return this.priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
