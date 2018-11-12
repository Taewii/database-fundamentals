package org.softuni.mostwanted.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.softuni.mostwanted.models.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town,Integer> {

    Town getTownByName(String name);

    //todo
//    @Query(value = "SELECT NEW " +
//            "org.softuni.mostwanted.models.dtos.view.json.RacingTownsJsonExportDTO(r.homeTown.name, COUNT(r.homeTown.id)) " +
//            "FROM Racer AS r " +
//            "GROUP BY r.homeTown.id " +
//            "ORDER BY COUNT(r.homeTown.id) DESC, r.homeTown.name ASC")
//    List<RacingTownsJsonExportDTO> getRacingTownsWithRacersCount();

}