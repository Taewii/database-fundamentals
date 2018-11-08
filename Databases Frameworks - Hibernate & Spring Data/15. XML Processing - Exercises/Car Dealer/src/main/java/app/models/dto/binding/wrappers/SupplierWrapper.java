package app.models.dto.binding.wrappers;

import app.models.dto.binding.SupplierDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierWrapper implements Serializable {

    @XmlElement(name = "supplier")
    private SupplierDto[] suppliers;

    public SupplierWrapper() {
    }

    public SupplierDto[] getSuppliers() {
        return this.suppliers;
    }
}
