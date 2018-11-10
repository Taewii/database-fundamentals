package org.softuni.mostwanted.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {

    private Integer id;
    private Integer laps;
    private District district;
    private Set<RaceEntry> entries;

    public Race() {
        this.entries = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false, columnDefinition = "INT(11) NOT NULL DEFAULT 0")
    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne(optional = false)
    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race")
    public Set<RaceEntry> getEntries() {
        return this.entries;
    }

    @OneToMany
    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
