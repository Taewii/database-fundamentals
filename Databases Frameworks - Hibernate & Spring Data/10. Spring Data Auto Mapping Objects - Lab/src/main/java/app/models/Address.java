package app.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Set<Employee> citizens;

    public Address() {
        this.citizens = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Employee> getCitizens() {
        return this.citizens;
    }

    public void setCitizens(Set<Employee> citizens) {
        this.citizens = citizens;
    }
}
