package org.softuni.mostwanted.services.car;

import org.softuni.mostwanted.models.dtos.binding.json.CarJsonImportDTO;
import org.softuni.mostwanted.models.entities.Car;

public interface CarService {
    String create(CarJsonImportDTO carDto);

    Car getCarById(Integer id);
}