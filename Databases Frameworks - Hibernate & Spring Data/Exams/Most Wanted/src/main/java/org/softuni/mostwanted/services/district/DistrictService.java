package org.softuni.mostwanted.services.district;

import org.softuni.mostwanted.models.dtos.binding.json.DistrictJsonImportDTO;
import org.softuni.mostwanted.models.entities.District;

public interface DistrictService {

    String create(DistrictJsonImportDTO districtDto);

    District getDistrictByName(String name);
}