package org.softuni.mostwanted;

public final class Config {

    private static final String DIR = System.getProperty("user.dir") + "/src/main/resources";

    public static final String CARS_IMPORT_JSON = "/files/json/input/cars.json";
    public static final String DISTRICTS_IMPORT_JSON = "/files/json/input/districts.json";
    public static final String RACERS_IMPORT_JSON = "/files/json/input/racers.json";
    public static final String TOWNS_IMPORT_JSON = "/files/json/input/towns.json";

    public static final String RACE_ENTRIES_IMPORT_XML = "/files/xml/input/race-entries.xml";
    public static final String RACES_IMPORT_XML = "/files/xml/input/races.xml";

    public static final String TOWNS_BY_RACERS_EXPORT_JSON = DIR + "/files/json/output/townsByRacers.json";
    public static final String RACERS_WITH_CARS_EXPORT_JSON = DIR + "/files/json/output/racingCars.json";

    public static final String MOST_WANTED_RACER_EXPORT_XML = DIR + "/files/xml/output/mostWantedRacer.xml";

    public static final String DUPLICATE_DATA_ERROR = "Error: Duplicate Data!";
    public static final String INVALID_DATA_ERROR = "Error: Incorrect Data!";
    public static final String SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s – %s.";

    private Config() {
    }
}