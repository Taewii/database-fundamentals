package org.softuni.ruk.services.bankAccount;

import org.softuni.ruk.models.dtos.binding.xml.BankAccountXmlImportDTO;
import org.softuni.ruk.models.entities.BankAccount;

public interface BankAccountService {

    String create(BankAccountXmlImportDTO bankAccountDto);

    BankAccount getAccountByNumber(String number);
}