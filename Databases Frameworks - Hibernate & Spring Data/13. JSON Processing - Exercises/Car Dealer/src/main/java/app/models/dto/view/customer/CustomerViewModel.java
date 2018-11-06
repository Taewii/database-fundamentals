package app.models.dto.view.customer;

import app.models.dto.view.sale.SaleViewModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class CustomerViewModel implements Serializable {

    private Long id;
    private String name;
    private Date birthDate;
    private boolean isYoungDriver;
    private Set<SaleViewModel> sales;

    public CustomerViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewModel> getSales() {
        return this.sales;
    }

    public void setSales(Set<SaleViewModel> sales) {
        this.sales = sales;
    }
}
