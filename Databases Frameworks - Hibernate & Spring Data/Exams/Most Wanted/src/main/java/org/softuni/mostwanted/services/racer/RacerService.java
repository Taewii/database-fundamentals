package org.softuni.mostwanted.services.racer;

import org.softuni.mostwanted.models.dtos.binding.json.RacerJsonImportDTO;
import org.softuni.mostwanted.models.dtos.view.json.RacingCarsJsonExportDTO;
import org.softuni.mostwanted.models.dtos.view.json.RacingTownsJsonExportDTO;
import org.softuni.mostwanted.models.entities.Racer;

import java.util.List;

public interface RacerService {

    String create(RacerJsonImportDTO racerDto);

    Racer getRacerByName(String name);

    List<RacingCarsJsonExportDTO> racersWithAtleastOneCar();

    Racer getMostWantedRacer();

    List<RacingTownsJsonExportDTO> racingTownsWithRacersCount();
}