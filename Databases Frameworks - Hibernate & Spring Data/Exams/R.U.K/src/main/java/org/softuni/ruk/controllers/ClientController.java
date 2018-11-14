package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.ClientJsonImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ClientController {

    private final ClientService clientService;
    private final Parser jsonParser;

    @Autowired
    public ClientController(ClientService clientService, Parser jsonParser) {
        this.clientService = clientService;
        this.jsonParser = jsonParser;
    }

    public String importClientsFromJson(String content) {
        try {
            ClientJsonImportDTO[] clients = this.jsonParser.read(ClientJsonImportDTO[].class, content);

            StringBuilder sb = new StringBuilder();
            for (ClientJsonImportDTO client : clients) {
                sb.append(this.clientService.create(client)).append(System.lineSeparator());
            }

            return sb.toString().trim();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
