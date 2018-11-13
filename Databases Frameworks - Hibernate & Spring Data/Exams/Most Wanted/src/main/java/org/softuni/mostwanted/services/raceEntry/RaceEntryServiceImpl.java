package org.softuni.mostwanted.services.raceEntry;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.xml.RaceEntryXmlImportDTO;
import org.softuni.mostwanted.models.dtos.view.xml.EntryXmlExportDTO;
import org.softuni.mostwanted.models.dtos.view.xml.MostWantedRacerDTO;
import org.softuni.mostwanted.models.dtos.view.xml.RacerXmlExportDTO;
import org.softuni.mostwanted.models.entities.Car;
import org.softuni.mostwanted.models.entities.RaceEntry;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.services.car.CarService;
import org.softuni.mostwanted.services.racer.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {

    private final RaceEntryRepository raceEntryRepository;
    private final ModelParser modelParser;
    private final CarService carService;
    private final RacerService racerService;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository,
                                ModelParser modelParser,
                                CarService carService,
                                RacerService racerService) {
        this.raceEntryRepository = raceEntryRepository;
        this.modelParser = modelParser;
        this.carService = carService;
        this.racerService = racerService;
    }

    @Override
    public String create(RaceEntryXmlImportDTO raceDto) {
        Car car = this.carService.getCarById(raceDto.getCarId());
        Racer racer = this.racerService.getRacerByName(raceDto.getRacer());

        if (car == null || racer == null) {
            return Config.INVALID_DATA_ERROR;
        }

        RaceEntry raceEntry = this.modelParser.convert(raceDto, RaceEntry.class);

        raceEntry.setCar(car);
        raceEntry.setRacer(racer);
        raceEntry.setRace(null);

        this.raceEntryRepository.saveAndFlush(raceEntry);

        //not sure why getId() is producing random id's
        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "RaceEntry", raceEntry.getId());
    }

    @Override
    public RaceEntry getRaceEntryById(Integer id) {
        return this.raceEntryRepository.findOne(id);
    }

    @Override
    public List<RaceEntry> findAll() {
        return this.raceEntryRepository.findAll();
    }

    @Override
    public MostWantedRacerDTO exportMostWantedRacer() {
        Racer racer = this.racerService.getMostWantedRacer();
        List<RaceEntry> raceEntries = this.raceEntryRepository.findAll()
                .stream()
                .filter(re -> re.getRacer().getId() == racer.getId())
                .collect(Collectors.toList());

        List<EntryXmlExportDTO> entriesDto = raceEntries.stream().map(entry -> {
            String carInfo = String.format("%s %s @ %d",
                    entry.getCar().getBrand(),
                    entry.getCar().getModel(),
                    entry.getCar().getYearOfProduction());

            EntryXmlExportDTO dto = new EntryXmlExportDTO();
            dto.setCar(carInfo);
            dto.setFinishTime(entry.getFinishTime());
            return dto;
        }).collect(Collectors.toList());

        RacerXmlExportDTO racerDto = new RacerXmlExportDTO();
        racerDto.setName(racer.getName());
        racerDto.setEntries(entriesDto.stream()
                .sorted(Comparator.comparing(EntryXmlExportDTO::getFinishTime))
                .collect(Collectors.toList()));

        MostWantedRacerDTO mostWantedRacerDTO = new MostWantedRacerDTO();
        mostWantedRacerDTO.setRacer(racerDto);

        return mostWantedRacerDTO;
    }
}