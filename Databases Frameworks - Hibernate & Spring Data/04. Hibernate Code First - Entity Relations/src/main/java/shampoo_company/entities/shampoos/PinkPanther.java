package shampoo_company.entities.shampoos;

import shampoo_company.entities.labels.BasicLabel;
import shampoo_company.enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    private static final String PINK_PANTHER = "Pink Panther";
    private static final BigDecimal PRICE = new BigDecimal("8.50");
    private static final Size SIZE = Size.MEDIUM;

    public PinkPanther() {
    }

    public PinkPanther(BasicLabel classicLabel) {
        super(PINK_PANTHER, PRICE, SIZE, classicLabel);
    }
}
