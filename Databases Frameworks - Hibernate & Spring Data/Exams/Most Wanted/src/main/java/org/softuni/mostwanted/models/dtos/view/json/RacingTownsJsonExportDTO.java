package org.softuni.mostwanted.models.dtos.view.json;

import java.io.Serializable;

public class RacingTownsJsonExportDTO implements Serializable {

    private String name;
    private Long racers;

    public RacingTownsJsonExportDTO() {
    }

    public RacingTownsJsonExportDTO(String name, Long racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRacers() {
        return this.racers;
    }

    public void setRacers(Long racers) {
        this.racers = racers;
    }
}
