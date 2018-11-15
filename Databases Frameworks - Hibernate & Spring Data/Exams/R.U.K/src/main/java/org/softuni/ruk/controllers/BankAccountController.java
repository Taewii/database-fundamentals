package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.binding.xml.BankAccountWrapper;
import org.softuni.ruk.models.dtos.binding.xml.BankAccountXmlImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.bankAccount.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final Parser xmlParser;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, Parser xmlParser) {
        this.bankAccountService = bankAccountService;
        this.xmlParser = xmlParser;
    }

    public String importBankAccountsFromXml(String content) {
        BankAccountWrapper wrapper = this.xmlParser.read(BankAccountWrapper.class, content);

        StringBuilder sb = new StringBuilder();
        for (BankAccountXmlImportDTO bankAccount : wrapper.getBankAccounts()) {
            sb.append(this.bankAccountService.create(bankAccount)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
