package app.services.car;

import app.models.dto.binding.CarDto;

public interface CarService {
    void saveAll(CarDto[] carDtos);
}