package app.models.dto.view.car;

import java.io.Serializable;

public class CarViewModelWithoutPartsAndId implements Serializable {

    private String make;
    private String model;
    private Long travelledDistance;

    public CarViewModelWithoutPartsAndId() {
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
}
