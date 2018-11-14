package org.softuni.ruk;

public final class Constants {

    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/main/resources";

    public static final String BRANCHES_IMPORT_JSON = "/files/json/input/branches.json";
    public static final String CLIENTS_IMPORT_JSON = "/files/json/input/clients.json";
    public static final String EMPLOYEES_IMPORT_JSON = "/files/json/input/employees.json";

    public static final String BANK_ACCOUNTS_ENTRIES_IMPORT_XML = "/files/xml/input/bank-accounts.xml";
    public static final String CARDS_IMPORT_XML = "/files/xml/input/cards.xml";

    public static final String TOP_EMPLOYEES_EXPORT_JSON = RESOURCES_PATH + "/files/json/output/topEmployees.json";

    public static final String FAMILY_GUY_EXPORT_XML = RESOURCES_PATH + "/files/xml/output/family-guy.xml";

    public static final String INVALID_DATA_ERROR = "Error: Incorrect Data!";
    public static final String SUCCESSFUL_IMPORT_MESSAGE = "Successfully imported %s – %s.";

    private Constants() {
    }
}
