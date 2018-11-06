package app.models.dto.view.car;

import app.models.dto.view.part.PartViewModel;

import java.io.Serializable;
import java.util.Set;

public class CarViewModelWithParts implements Serializable {

    private String make;
    private String model;
    private Long travelledDistance;
    private Set<PartViewModel> parts;

    public CarViewModelWithParts() {
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartViewModel> getParts() {
        return this.parts;
    }

    public void setParts(Set<PartViewModel> parts) {
        this.parts = parts;
    }
}

