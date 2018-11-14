package org.softuni.ruk.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "branches")
public class Branch {

    private Long id;
    private String name;

    public Branch() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
