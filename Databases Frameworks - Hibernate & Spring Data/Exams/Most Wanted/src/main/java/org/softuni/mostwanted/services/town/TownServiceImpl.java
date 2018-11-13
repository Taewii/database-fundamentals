package org.softuni.mostwanted.services.town;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.json.TownJsonImportDTO;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           ValidationUtil validation,
                           ModelParser modelParser) {
        this.townRepository = townRepository;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(TownJsonImportDTO townDto) {
        if (this.townRepository.getTownByName(townDto.getName()) != null) {
            return Config.DUPLICATE_DATA_ERROR;
        }

        if (!this.validation.isValid(townDto)) {
            return Config.INVALID_DATA_ERROR;
        }

        Town town = this.modelParser.convert(townDto, Town.class);
        this.townRepository.saveAndFlush(town);

        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "Town", town.getName());
    }

    @Override
    public Town getTownByName(String name) {
        return this.townRepository.getTownByName(name);
    }
}