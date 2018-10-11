package shampoo_company.entities.ingredients.chemical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {

    private static final String FORMULA = "NH4Cl";
    private static final BigDecimal PRICE = new BigDecimal("6.12");
    private static final String NAME = "Ammonium Chloride";

    public AmmoniumChloride() {
        super(NAME, PRICE, FORMULA);
    }
}
