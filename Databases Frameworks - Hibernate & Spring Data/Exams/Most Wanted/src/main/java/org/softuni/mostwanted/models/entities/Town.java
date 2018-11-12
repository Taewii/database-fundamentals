package org.softuni.mostwanted.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    private Integer id;
    private String name;

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
}
