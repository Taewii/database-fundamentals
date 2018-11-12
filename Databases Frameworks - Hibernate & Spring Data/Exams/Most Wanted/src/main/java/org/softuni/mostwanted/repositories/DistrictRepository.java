package org.softuni.mostwanted.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.softuni.mostwanted.models.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    District getDistinctByName(String name);
}