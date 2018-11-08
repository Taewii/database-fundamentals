package app.services.car;

import app.models.dto.binding.CarDto;
import app.models.dto.view.car.CarViewModel;
import app.models.dto.view.car.CarViewModelWithParts;

import java.util.List;

public interface CarService {
    void saveAll(CarDto[] carDtos);

    List<CarViewModel> carsByBrandName(String brand);

    List<CarViewModelWithParts> carsWithParts();
}