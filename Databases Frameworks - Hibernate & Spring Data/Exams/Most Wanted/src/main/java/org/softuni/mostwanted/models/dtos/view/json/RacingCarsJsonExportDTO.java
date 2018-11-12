package org.softuni.mostwanted.models.dtos.view.json;

import java.io.Serializable;
import java.util.List;

public class RacingCarsJsonExportDTO implements Serializable {

    private String name;
    private Integer age;
    private List<String> cars;

    public RacingCarsJsonExportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getCars() {
        return this.cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }
}