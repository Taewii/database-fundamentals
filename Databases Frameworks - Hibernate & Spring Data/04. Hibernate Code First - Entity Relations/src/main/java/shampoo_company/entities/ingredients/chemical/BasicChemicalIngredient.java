package shampoo_company.entities.ingredients.chemical;

import shampoo_company.contracts.ChemicalIngredient;
import shampoo_company.entities.ingredients.basic.BasicIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {

    @Column(name = "chemical_formula")
    private String chemicalFormula;

    public BasicChemicalIngredient() {
    }

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.setChemicalFormula(chemicalFormula);
    }

    @Override
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
