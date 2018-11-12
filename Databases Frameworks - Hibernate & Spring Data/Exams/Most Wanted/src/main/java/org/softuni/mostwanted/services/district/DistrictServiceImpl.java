package org.softuni.mostwanted.services.district;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.json.DistrictJsonImportDTO;
import org.softuni.mostwanted.models.entities.District;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.services.town.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final TownService townService;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               TownService townService,
                               ValidationUtil validation,
                               ModelParser modelParser) {
        this.districtRepository = districtRepository;
        this.townService = townService;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(DistrictJsonImportDTO districtDto) {
        if (this.districtRepository.getDistinctByName(districtDto.getName()) != null) {
            return Config.DUPLICATE_DATA_ERROR;
        }

        if (!this.validation.isValid(districtDto)) {
            return Config.INVALID_DATA_ERROR;
        }

        Town town = this.townService.getTownByName(districtDto.getTownName());
        District district = this.modelParser.convert(districtDto, District.class);
        district.setTown(town);
        this.districtRepository.saveAndFlush(district);

        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "District", district.getName());
    }

    @Override
    public District getDistrictByName(String name) {
        return this.districtRepository.getDistinctByName(name);
    }
}