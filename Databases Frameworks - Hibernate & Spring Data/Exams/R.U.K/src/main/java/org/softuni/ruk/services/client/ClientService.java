package org.softuni.ruk.services.client;

import org.softuni.ruk.models.dtos.binding.json.ClientJsonImportDTO;
import org.softuni.ruk.models.dtos.view.xml.FamilyGuyXmlExportDTO;
import org.softuni.ruk.models.entities.Client;

import java.util.List;

public interface ClientService {

    String create(ClientJsonImportDTO clientDto);

    List<Client> getClientByFullName(String fullName);

    FamilyGuyXmlExportDTO getTheFamilyGuy();
}