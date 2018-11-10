package org.softuni.mostwanted.services.car;

import org.softuni.mostwanted.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	private final CarRepository carRepository;

	@Autowired
	public CarServiceImpl(CarRepository carRepository) { 
		this.carRepository = carRepository;
	}

}