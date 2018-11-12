package org.softuni.mostwanted.services.car;

import org.softuni.mostwanted.Config;
import org.softuni.mostwanted.models.dtos.binding.json.CarJsonImportDTO;
import org.softuni.mostwanted.models.entities.Car;
import org.softuni.mostwanted.models.entities.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.services.racer.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final RacerService racerService;
    private final ModelParser modelParser;
    private final ValidationUtil validation;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          RacerService racerService,
                          ModelParser modelParser,
                          ValidationUtil validation) {
        this.carRepository = carRepository;
        this.racerService = racerService;
        this.modelParser = modelParser;
        this.validation = validation;
    }

    @Override
    public String create(CarJsonImportDTO carDto) {
        if (!this.validation.isValid(carDto)) {
            return Config.INVALID_DATA_ERROR;
        }

        Racer racer = this.racerService.getRacerByName(carDto.getRacerName());
        Car car = this.modelParser.convert(carDto, Car.class);
        car.setRacer(racer);

        this.carRepository.saveAndFlush(car);
        if (racer != null) {
            racer.getCars().add(car);
        }

        String carMessage = String.format("%s %s @ %d", car.getBrand(), car.getModel(), car.getYearOfProduction());
        return String.format(Config.SUCCESSFUL_IMPORT_MESSAGE, "Car", carMessage);
    }

    @Override
    public Car getCarById(Integer id) {
        return this.carRepository.findOne(id);
    }
}