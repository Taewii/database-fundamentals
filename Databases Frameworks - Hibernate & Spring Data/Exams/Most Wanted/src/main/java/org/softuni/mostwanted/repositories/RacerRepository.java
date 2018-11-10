package org.softuni.mostwanted.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.softuni.mostwanted.models.entities.Racer;

@Repository
public interface RacerRepository extends JpaRepository<Racer,Integer> {


}