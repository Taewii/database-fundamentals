package org.softuni.mostwanted.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.softuni.mostwanted.models.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {


}