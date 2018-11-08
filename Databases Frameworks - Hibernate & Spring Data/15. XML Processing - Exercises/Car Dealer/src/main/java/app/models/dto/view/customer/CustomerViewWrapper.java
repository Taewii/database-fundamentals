package app.models.dto.view.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerViewWrapper {

    @XmlElement(name = "customer")
    private List<CustomerViewModel> customers;

    public CustomerViewWrapper() {
    }

    public CustomerViewWrapper(List<CustomerViewModel> customers) {
        this.customers = customers;
    }
}
