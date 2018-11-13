package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.models.dtos.view.json.RacingTownsJsonExportDTO;
import org.softuni.mostwanted.models.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    Racer getRacerByName(String name);

    @Query(value = "SELECT r FROM Racer AS r WHERE r.cars.size > 0")
    List<Racer> getAllRacersWithCars();

    @Query(value =
            "SELECT r FROM RaceEntry AS re " +
                    "JOIN re.racer AS r " +
                    "GROUP BY re.racer " +
                    "ORDER BY COUNT (re.racer) DESC")
    List<Racer> mostWantedRacers();

    @Query(value =
            "SELECT new org.softuni.mostwanted.models.dtos.view.json.RacingTownsJsonExportDTO(h.name, COUNT(h.id)) " +
                    "FROM Racer as r " +
                    "JOIN r.homeTown as h " +
                    "GROUP BY h.id " +
                    "ORDER BY COUNT(h.id) DESC, h.name ASC")
    List<RacingTownsJsonExportDTO> getRacingTownsWithRacersCount();
}