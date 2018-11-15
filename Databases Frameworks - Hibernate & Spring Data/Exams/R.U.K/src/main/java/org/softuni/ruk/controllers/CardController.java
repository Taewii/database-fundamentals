package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.binding.xml.CardWrapper;
import org.softuni.ruk.models.dtos.binding.xml.CardXmlImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CardController {

    private final CardService cardService;
    private final Parser xmlParser;

    @Autowired
    public CardController(CardService cardService, Parser xmlParser) {
        this.cardService = cardService;
        this.xmlParser = xmlParser;
    }

    public String importCardsFromXml(String content) {
        CardWrapper wrapper = this.xmlParser.read(CardWrapper.class, content);

        StringBuilder sb = new StringBuilder();
        for (CardXmlImportDTO card : wrapper.getCards()) {
            sb.append(this.cardService.create(card)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
