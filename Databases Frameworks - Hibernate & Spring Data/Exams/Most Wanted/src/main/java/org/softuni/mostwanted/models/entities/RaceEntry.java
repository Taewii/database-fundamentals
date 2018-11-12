package org.softuni.mostwanted.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    private Integer id;
    private Boolean hasFinished;
    private Double finishTime;
    private Car car;
    private Racer racer;
    private Race race;

    public RaceEntry() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    @ManyToOne
    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    public Racer getRacer() {
        return this.racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    @ManyToOne
    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
