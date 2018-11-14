package org.softuni.ruk.services.client;

import org.softuni.ruk.models.dtos.ClientJsonImportDTO;

public interface ClientService {

    String create(ClientJsonImportDTO clientDto);
}