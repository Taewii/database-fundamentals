package app.models.dto.view.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerTotalSalesViewModelWrapper implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesViewModel> customers;

    public CustomerTotalSalesViewModelWrapper() {
    }

    public CustomerTotalSalesViewModelWrapper(List<CustomerTotalSalesViewModel> customers) {
        this.customers = customers;
    }
}
