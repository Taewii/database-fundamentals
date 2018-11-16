package app.petclinic.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "animal_aids")
public class AnimalAid {

    private Long id;
    private String name;
    private BigDecimal price;
    private List<Procedure> procedures;

    public AnimalAid() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "animal_aids_procedures",
            joinColumns = @JoinColumn(name = "animal_aid_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    public List<Procedure> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
