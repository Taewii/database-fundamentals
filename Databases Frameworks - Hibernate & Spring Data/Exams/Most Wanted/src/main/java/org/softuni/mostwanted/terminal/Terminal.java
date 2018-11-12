package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;

    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    TownController townController,
                    DistrictController districtController,
                    RacerController racerController,
                    CarController carController,
                    RaceEntryController raceEntryController,
                    RaceController raceController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
    }

    @Override
    public void run(String... args) {
        try {
            //import
            this.consoleIO.write(this.townController.importDataFromJson(this.fileIO.read(Config.TOWNS_IMPORT_JSON)));
            this.consoleIO.write(this.districtController.importDataFromJson(this.fileIO.read(Config.DISTRICTS_IMPORT_JSON)));
            this.consoleIO.write(this.racerController.importDataFromJson(this.fileIO.read(Config.RACERS_IMPORT_JSON)));
            this.consoleIO.write(this.carController.importDataFromJson(this.fileIO.read(Config.CARS_IMPORT_JSON)));
            this.consoleIO.write(this.raceEntryController.importDataFromXml(this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML)));
            this.consoleIO.write(this.raceController.importDataFromXml(this.fileIO.read(Config.RACES_IMPORT_XML)));

            //export
            this.fileIO.write(this.townController.exportRacerTownsAsJson(), Config.TOWNS_BY_RACERS_EXPORT_JSON);
            //TODO ↓↓↓↓↓↓
            //this.fileIO.write(this.racerController.exportRacersWithCars(), Config.RACERS_WITH_CARS_EXPORT_JSON);
            this.fileIO.write(this.raceEntryController.exportMostWantedRacer(), Config.MOST_WANTED_RACER_EXPORT_XML);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
