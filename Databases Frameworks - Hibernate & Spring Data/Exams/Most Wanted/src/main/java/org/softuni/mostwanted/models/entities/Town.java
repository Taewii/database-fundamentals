package org.softuni.mostwanted.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private Integer id;
    private String name;
    private Set<District> districts;
    private Set<Racer> citizens;

    public Town() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "town")
    public Set<District> getDistricts() {
        return this.districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @OneToMany(mappedBy = "homeTown")
    public Set<Racer> getCitizens() {
        return this.citizens;
    }

    public void setCitizens(Set<Racer> citizens) {
        this.citizens = citizens;
    }
}
