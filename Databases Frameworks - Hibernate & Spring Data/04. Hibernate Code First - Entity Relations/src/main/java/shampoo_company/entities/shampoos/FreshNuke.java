package shampoo_company.entities.shampoos;

import shampoo_company.entities.labels.BasicLabel;
import shampoo_company.enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    private static final String FRESH_NUKE = "Fresh Nuke";
    private static final BigDecimal PRICE = new BigDecimal("9.33");
    private static final Size SIZE = Size.LARGE;

    public FreshNuke() {
    }

    public FreshNuke(BasicLabel classicLabel) {
        super(FRESH_NUKE, PRICE, SIZE, classicLabel);
    }
}
