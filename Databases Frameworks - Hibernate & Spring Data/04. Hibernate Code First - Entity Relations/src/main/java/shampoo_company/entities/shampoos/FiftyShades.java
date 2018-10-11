package shampoo_company.entities.shampoos;

import shampoo_company.entities.labels.BasicLabel;
import shampoo_company.enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShades extends BasicShampoo {

    private static final String FIFTY_SHADES = "Fifty Shades";
    private static final BigDecimal PRICE = new BigDecimal("6.69");
    private static final Size SIZE = Size.SMALL;

    public FiftyShades() {
    }

    public FiftyShades(BasicLabel classicLabel) {
        super(FIFTY_SHADES, PRICE, SIZE, classicLabel);
    }
}
