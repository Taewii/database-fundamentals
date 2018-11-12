package org.softuni.mostwanted.services.race;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.xml.RaceXmlImportDTO;
import org.softuni.mostwanted.models.entities.District;
import org.softuni.mostwanted.models.entities.Race;
import org.softuni.mostwanted.models.entities.RaceEntry;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.district.DistrictService;
import org.softuni.mostwanted.services.raceEntry.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;
    private final DistrictService districtService;
    private final RaceEntryService raceEntryService;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository,
                           DistrictService districtService,
                           RaceEntryService raceEntryService,
                           ModelParser modelParser,
                           ValidationUtil validation) {
        this.raceRepository = raceRepository;
        this.districtService = districtService;
        this.raceEntryService = raceEntryService;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(RaceXmlImportDTO raceDto) {
        if (!this.validation.isValid(raceDto)) {
            return Config.INVALID_DATA_ERROR;
        }

        District district = this.districtService.getDistrictByName(raceDto.getDistrictName());
        Set<RaceEntry> raceEntries = raceDto.getEntries().stream()
                .map(e -> this.raceEntryService.getRaceEntryById(e.getId())).collect(Collectors.toSet());

        Race race = this.modelParser.convert(raceDto, Race.class);
        race.setDistrict(district);
        race.setEntries(raceEntries);

        this.raceRepository.saveAndFlush(race);
        race.getEntries().forEach(re -> re.setRace(race));

        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "Race", race.getId());
    }
}