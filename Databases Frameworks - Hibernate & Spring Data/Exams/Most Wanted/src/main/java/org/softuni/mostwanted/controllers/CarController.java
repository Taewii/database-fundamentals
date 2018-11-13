package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.models.dtos.binding.json.CarJsonImportDTO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {

    private final CarService carService;
    private final Parser jsonParser;

    @Autowired
    public CarController(CarService carService,
                         Parser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String content) throws IOException, JAXBException {
        CarJsonImportDTO[] cars = this.jsonParser.read(CarJsonImportDTO[].class, content);

        StringBuilder sb = new StringBuilder();
        for (CarJsonImportDTO car : cars) {
            sb.append(this.carService.create(car)).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
