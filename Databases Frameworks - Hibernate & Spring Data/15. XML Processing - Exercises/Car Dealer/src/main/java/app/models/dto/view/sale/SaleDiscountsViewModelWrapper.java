package app.models.dto.view.sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SaleDiscountsViewModelWrapper implements Serializable {

    @XmlElement(name = "sale")
    private List<SaleDiscountsViewModel> sales;

    public SaleDiscountsViewModelWrapper() {
    }

    public SaleDiscountsViewModelWrapper(List<SaleDiscountsViewModel> sales) {
        this.sales = sales;
    }
}
