package app.models.dto.view.supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SuppliersViewWrapper implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierViewModel> suppliers;

    public SuppliersViewWrapper() {
    }

    public SuppliersViewWrapper(List<SupplierViewModel> suppliers) {
        this.suppliers = suppliers;
    }
}
