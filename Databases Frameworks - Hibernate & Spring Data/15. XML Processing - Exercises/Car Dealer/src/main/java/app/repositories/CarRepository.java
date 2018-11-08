package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "SELECT * FROM cars ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Car getRandomCar();

    @Query(value = "SELECT c FROM Car AS c WHERE c.make = :brand ORDER BY c.model, c.travelledDistance DESC")
    List<Car> carsByBrandNameOrderedByModelAndTravelledDistance(@Param("brand") String brand);
}