package org.softuni.mostwanted.services.town;

import org.softuni.mostwanted.models.dtos.binding.json.TownJsonImportDTO;
import org.softuni.mostwanted.models.entities.Town;

public interface TownService {

    String create(TownJsonImportDTO town);

    Town getTownByName(String name);
}