package org.softuni.mostwanted.services.racer;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.json.RacerJsonImportDTO;
import org.softuni.mostwanted.models.dtos.view.json.RacingCarsJsonExportDTO;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.models.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.town.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {

    private final RacerRepository racerRepository;
    private final TownService townService;
    private final ValidationUtil validation;
    private final ModelParser modelParser;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository,
                            TownService townService,
                            ValidationUtil validation,
                            ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townService = townService;
        this.validation = validation;
        this.modelParser = modelParser;
    }

    @Override
    public String create(RacerJsonImportDTO racerDto) {
        if (this.racerRepository.getRacerByName(racerDto.getName()) != null) {
            return Config.DUPLICATE_DATA_ERROR;
        }

        if (!this.validation.isValid(racerDto)) {
            return Config.INVALID_DATA_ERROR;
        }

        Town town = this.townService.getTownByName(racerDto.getHomeTown());
        Racer racer = this.modelParser.convert(racerDto, Racer.class);
        racer.setHomeTown(town);
        this.racerRepository.saveAndFlush(racer);

        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racer.getName());
    }

    @Override
    public Racer getRacerByName(String name) {
        return this.racerRepository.getRacerByName(name);
    }

    public List<RacingCarsJsonExportDTO> racersWithAtleastOneCar() {
        List<Racer> racers = this.racerRepository.getAllRacersWithCars();
        List<RacingCarsJsonExportDTO> dtos = new ArrayList<>();

        for (Racer racer : racers) {
            RacingCarsJsonExportDTO dto = new RacingCarsJsonExportDTO();
            dto.setName(racer.getName());
            dto.setAge(racer.getAge());

            List<String> cars = racer.getCars().stream()
                    .map(c -> String.format("%s %s %s", c.getBrand(), c.getModel(), c.getYearOfProduction()))
                    .collect(Collectors.toList());

            dto.setCars(cars);

            dtos.add(dto);
        }

        return dtos.stream().sorted((a, b) -> {
            int result = Integer.compare(b.getCars().size(), a.getCars().size());

            if (result == 0) {
                result = a.getName().compareTo(b.getName());
            }

            return result;
        }).collect(Collectors.toList());
    }

    public Racer getMostWantedRacer() {
        return this.racerRepository.mostWantedRacers().stream().findFirst().orElse(null);
    }
}