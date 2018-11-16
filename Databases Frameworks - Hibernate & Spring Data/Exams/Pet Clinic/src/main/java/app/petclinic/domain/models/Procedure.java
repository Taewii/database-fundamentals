package app.petclinic.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "procedures")
public class Procedure {

    private Long id;
    private Animal animal;
    private BigDecimal cost;
    private Vet vet;
    private Date date;
    private List<AnimalAid> services;

    public Procedure() {
        this.services = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Transient
    public BigDecimal getCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (AnimalAid service : services) {
            total = total.add(service.getPrice());
        }
        return total;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @ManyToOne
    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    @Column(name = "date_performed")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToMany(mappedBy = "procedures", cascade = CascadeType.ALL)
    public List<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(List<AnimalAid> services) {
        this.services = services;
    }
}
