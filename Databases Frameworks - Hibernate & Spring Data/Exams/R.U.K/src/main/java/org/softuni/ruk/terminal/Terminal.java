package org.softuni.ruk.terminal;

import org.softuni.ruk.controllers.*;
import org.softuni.ruk.io.interfaces.ConsoleIO;
import org.softuni.ruk.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.softuni.ruk.Constants.*;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private final FileIO fileIO;
    private final ConsoleIO consoleIO;
    private final BranchController branchController;
    private final EmployeeController employeeController;
    private final ClientController clientController;
    private final BankAccountController bankAccountController;
    private final CardController cardController;

    @Autowired
    public Terminal(FileIO fileIO,
                    ConsoleIO consoleIO,
                    BranchController branchController,
                    EmployeeController employeeController,
                    ClientController clientController,
                    BankAccountController bankAccountController,
                    CardController cardController) {
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
        this.branchController = branchController;
        this.employeeController = employeeController;
        this.clientController = clientController;
        this.bankAccountController = bankAccountController;
        this.cardController = cardController;
    }

    @Override
    public void run(String... args) {

        //imports
        this.consoleIO.write(this.branchController.importBranchesFromJson(this.fileIO.read(BRANCHES_IMPORT_JSON)));
        this.consoleIO.write(this.employeeController.importEmployeesFromJson(this.fileIO.read(EMPLOYEES_IMPORT_JSON)));
        this.consoleIO.write(this.clientController.importClientsFromJson(this.fileIO.read(CLIENTS_IMPORT_JSON)));
        this.consoleIO.write(this.bankAccountController.importBankAccountsFromXml(this.fileIO.read(BANK_ACCOUNTS_IMPORT_XML)));
        this.consoleIO.write(this.cardController.importCardsFromXml(this.fileIO.read(CARDS_IMPORT_XML)));

        //export
        this.fileIO.write(this.employeeController.exportTopEmployees(), TOP_EMPLOYEES_EXPORT_JSON);
        this.fileIO.write(this.clientController.exportFamilyGuy(), FAMILY_GUY_EXPORT_XML);
    }
}
