package app.petclinic.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

    private Long id;
    private String name;
    private String type;
    private Integer age;
    private Passport passport;

    public Animal() {
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Passport getPassport() {
        return this.passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
