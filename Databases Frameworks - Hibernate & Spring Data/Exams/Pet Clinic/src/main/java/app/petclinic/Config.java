package app.petclinic;

public final class Config {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";

    public static final String ANIMAL_AIDS_IMPORT_JSON = "/files/json/input/animal_aids.json";
    public static final String ANIMALS_IMPORT_JSON = "/files/json/input/animals.json";
    public static final String VETS_IMPORT_XML = "/files/xml/input/vets.xml";
    public static final String PROCEDURES_IMPORT_XML = "/files/xml/input/procedures.xml";

    public static final String ANIMALS_BY_PHONE_NUMBER_EXPORT_JSON = RESOURCES_PATH + "/files/json/output/animals-by-phone-number.json";
    public static final String PROCEDURES_EXPORT_XML = RESOURCES_PATH + "/files/xml/output/procedures.xml";

    public static final String INVALID_DATA_MESSAGE = "Error: Invalid data.";

    public static final String VALID_RECORD_MESSAGE = "Record %s successfully imported.";
    public static final String ANIMAL_RECORD_MESSAGE = "Record %s Passport №: %s successfully imported.";

    private Config() {
    }
}
