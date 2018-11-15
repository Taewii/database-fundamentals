package org.softuni.ruk.services.bankAccount;

import org.softuni.ruk.Constants;
import org.softuni.ruk.models.dtos.binding.xml.BankAccountXmlImportDTO;
import org.softuni.ruk.models.entities.BankAccount;
import org.softuni.ruk.models.entities.Client;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.BankAccountRepository;
import org.softuni.ruk.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ClientService clientService;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  ClientService clientService,
                                  ValidationUtil validation,
                                  ModelParser modelParser) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientService = clientService;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(BankAccountXmlImportDTO bankAccountDto) {
        if (!this.validation.isValid(bankAccountDto)) {
            return Constants.INVALID_DATA_ERROR;
        }

        List<Client> client = this.clientService.getClientByFullName(bankAccountDto.getClient());

        if (client.isEmpty()) {
            return Constants.INVALID_DATA_ERROR;
        }

        BankAccount bankAccount = this.modelParser.convert(bankAccountDto, BankAccount.class);
        bankAccount.setClient(client.get(0));

        this.bankAccountRepository.saveAndFlush(bankAccount);
        client.forEach(c -> c.setBankAccount(bankAccount));

        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Bank Account", bankAccount.getAccountNumber());
    }

    public BankAccount getAccountByNumber(String number) {
        return this.bankAccountRepository.getBankAccountByAccountNumber(number);
    }
}