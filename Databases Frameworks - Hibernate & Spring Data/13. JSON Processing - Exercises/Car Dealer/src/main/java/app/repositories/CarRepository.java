package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "SELECT * FROM cars ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Car getRandomCar();
}