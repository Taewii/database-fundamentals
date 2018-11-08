package app.models.dto.binding.wrappers;

import app.models.dto.binding.CustomerDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomersWrapper implements Serializable {

    @XmlElement(name = "customer")
    private CustomerDto[] customers;

    public CustomersWrapper() {
    }

    public CustomerDto[] getCustomers() {
        return this.customers;
    }
}
