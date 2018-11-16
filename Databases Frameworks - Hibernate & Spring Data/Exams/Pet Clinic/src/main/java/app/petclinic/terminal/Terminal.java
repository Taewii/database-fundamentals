package app.petclinic.terminal;

import app.petclinic.controllers.AnimalAidController;
import app.petclinic.controllers.AnimalController;
import app.petclinic.controllers.ProcedureController;
import app.petclinic.controllers.VetController;
import app.petclinic.io.api.ConsoleIO;
import app.petclinic.io.api.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static app.petclinic.Config.*;

@Component
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final AnimalAidController animalAidController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final ProcedureController procedureController;


    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    AnimalAidController animalAidController,
                    AnimalController animalController,
                    VetController vetController,
                    ProcedureController procedureController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.vetController = vetController;
        this.procedureController = procedureController;
    }

    @Override
    public void run(String... strings) {
        //import
        this.consoleIO.write(this.animalAidController.importDataFromJSON(this.fileIO.read(ANIMAL_AIDS_IMPORT_JSON)));
        this.consoleIO.write(this.animalController.importDataFromJSON(this.fileIO.read(ANIMALS_IMPORT_JSON)));
        this.consoleIO.write(this.vetController.importDataFromXML(this.fileIO.read(VETS_IMPORT_XML)));
        this.consoleIO.write(this.procedureController.importDataFromXML(this.fileIO.read(PROCEDURES_IMPORT_XML)));

        //export
        // date format is "dd-MMM-yyyy" but prints the month in roman numerals for some reason.
        this.fileIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"), ANIMALS_BY_PHONE_NUMBER_EXPORT_JSON);
        this.consoleIO.write(this.animalController.exportAnimalsByOwnerPhoneNumber("0887446123"));
        this.fileIO.write(this.procedureController.exportProcedures(), PROCEDURES_EXPORT_XML);
        this.consoleIO.write(this.procedureController.exportProcedures());
    }
}
