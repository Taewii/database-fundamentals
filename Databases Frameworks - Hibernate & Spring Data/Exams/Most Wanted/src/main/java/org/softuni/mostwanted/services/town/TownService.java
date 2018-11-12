package org.softuni.mostwanted.services.town;

import org.softuni.mostwanted.models.dtos.binding.json.TownJsonImportDTO;
import org.softuni.mostwanted.models.dtos.view.json.RacingTownsJsonExportDTO;
import org.softuni.mostwanted.models.entities.Town;

import java.util.List;

public interface TownService {
    String create(TownJsonImportDTO town);

    Town getTownByName(String name);

    List<RacingTownsJsonExportDTO> getRacingTownsWithRacersCount();
}