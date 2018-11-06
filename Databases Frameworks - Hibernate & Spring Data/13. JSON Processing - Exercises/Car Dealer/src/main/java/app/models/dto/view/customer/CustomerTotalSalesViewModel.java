package app.models.dto.view.customer;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerTotalSalesViewModel implements Serializable {

    private String fullName;
    private int boughtCars;
    private BigDecimal spentMoney;

    public CustomerTotalSalesViewModel() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return this.spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
