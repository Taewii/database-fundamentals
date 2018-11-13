package org.softuni.mostwanted.services.raceEntry;

import org.softuni.mostwanted.models.dtos.binding.xml.RaceEntryXmlImportDTO;
import org.softuni.mostwanted.models.dtos.view.xml.MostWantedRacerDTO;
import org.softuni.mostwanted.models.entities.RaceEntry;

import java.util.List;

public interface RaceEntryService {

    String create(RaceEntryXmlImportDTO raceDto);

    RaceEntry getRaceEntryById(Integer id);

    List<RaceEntry> findAll();

    MostWantedRacerDTO exportMostWantedRacer();
}