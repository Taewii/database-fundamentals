package org.softuni.ruk.services.card;

import org.softuni.ruk.models.dtos.binding.xml.CardXmlImportDTO;

public interface CardService {

    String create(CardXmlImportDTO cardDto);
}