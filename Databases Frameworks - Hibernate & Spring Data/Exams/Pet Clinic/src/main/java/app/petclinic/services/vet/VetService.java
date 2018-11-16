package app.petclinic.services.vet;

import app.petclinic.domain.dto.binding.xml.VetXMLImportDTO;
import app.petclinic.domain.models.Vet;

public interface VetService {

    String create(VetXMLImportDTO vetDto);

    Vet findOneByName(String name);
}