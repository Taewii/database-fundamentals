package app.services.car;

import app.models.dto.binding.CarDto;
import app.models.dto.view.car.CarViewModel;
import app.models.dto.view.car.CarViewModelWithParts;
import app.models.entity.Car;
import app.models.entity.Part;
import app.repositories.CarRepository;
import app.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          PartRepository partRepository,
                          ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(CarDto[] carDtos) {
        List<Car> cars = Arrays.stream(carDtos)
                .map(c -> this.modelMapper.map(c, Car.class))
                .collect(Collectors.toList());
        Random random = new Random();

        for (Car car : cars) {
            List<Part> parts = this.partRepository.get20RandomParts();
            for (Part part : parts) {
                car.getParts().add(part);
                part.getCars().add(car);
                if (random.nextInt(5) == 0 && car.getParts().size() >= 15) {
                    break;
                }
            }
        }

        this.carRepository.saveAll(cars);
    }

    @Override
    public List<CarViewModel> carsByBrandName(String brand) {
        List<Car> cars = this.carRepository.carsByBrandNameOrderedByModelAndTravelledDistance(brand);

        return cars.stream().map(c -> this.modelMapper.map(c, CarViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarViewModelWithParts> carsWithParts() {
        List<Car> cars = this.carRepository.findAll();
        return cars.stream()
                .map(c -> this.modelMapper.map(c, CarViewModelWithParts.class))
                .collect(Collectors.toList());
    }
}