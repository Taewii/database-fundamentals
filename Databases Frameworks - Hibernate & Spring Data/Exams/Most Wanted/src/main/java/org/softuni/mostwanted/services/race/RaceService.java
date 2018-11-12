package org.softuni.mostwanted.services.race;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceXmlImportDTO;

public interface RaceService {
    String create(RaceXmlImportDTO raceDto);
}