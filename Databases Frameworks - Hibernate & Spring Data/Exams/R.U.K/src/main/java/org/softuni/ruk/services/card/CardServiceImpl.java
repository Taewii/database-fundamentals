package org.softuni.ruk.services.card;

import org.softuni.ruk.Constants;
import org.softuni.ruk.models.dtos.binding.xml.CardXmlImportDTO;
import org.softuni.ruk.models.entities.BankAccount;
import org.softuni.ruk.models.entities.Card;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.CardRepository;
import org.softuni.ruk.services.bankAccount.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final BankAccountService bankAccountService;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository,
                           BankAccountService bankAccountService,
                           ValidationUtil validation,
                           ModelParser modelParser) {
        this.cardRepository = cardRepository;
        this.bankAccountService = bankAccountService;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(CardXmlImportDTO cardDto) {
        if (!this.validation.isValid(cardDto)) {
            return Constants.INVALID_DATA_ERROR;
        }

        BankAccount bankAccount = this.bankAccountService.getAccountByNumber(cardDto.getAccountNumber());

        if (bankAccount == null) {
            return Constants.INVALID_DATA_ERROR;
        }

        Card card = this.modelParser.convert(cardDto, Card.class);
        card.setBankAccount(bankAccount);
        this.cardRepository.saveAndFlush(card);

        bankAccount.getCards().add(card);

        return String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Card", card.getCardNumber());
    }
}