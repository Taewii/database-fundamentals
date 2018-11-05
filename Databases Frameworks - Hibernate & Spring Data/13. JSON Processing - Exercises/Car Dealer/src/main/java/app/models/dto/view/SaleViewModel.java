package app.models.dto.view;

import app.models.dto.binding.CustomerDto;

import java.io.Serializable;

public class SaleViewModel implements Serializable {

    private Double discount;
    private CarViewModel car;
    private CustomerDto customer;

    public SaleViewModel() {
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CarViewModel getCar() {
        return this.car;
    }

    public void setCar(CarViewModel car) {
        this.car = car;
    }

    public CustomerDto getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
