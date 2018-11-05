package app.services.car;

import app.models.dto.binding.CarDto;
import app.models.dto.view.CarViewModel;

import java.util.List;

public interface CarService {
    void saveAll(CarDto[] carDtos);

    List<CarViewModel> carsByBrandName(String brand);
}