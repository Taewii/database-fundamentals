package org.softuni.ruk.controllers;

import org.softuni.ruk.models.dtos.binding.json.ClientJsonImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {

    private final ClientService clientService;
    private final Parser jsonParser;
    private final Parser xmlParser;

    @Autowired
    public ClientController(ClientService clientService,
                            Parser jsonParser,
                            Parser xmlParser) {
        this.clientService = clientService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importClientsFromJson(String content) {
        ClientJsonImportDTO[] clients = this.jsonParser.read(ClientJsonImportDTO[].class, content);

        StringBuilder sb = new StringBuilder();
        for (ClientJsonImportDTO client : clients) {
            sb.append(this.clientService.create(client)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    public String exportFamilyGuy() {
        return this.xmlParser.write(this.clientService.getTheFamilyGuy());
    }
}
