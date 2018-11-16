package app.petclinic.repositories;

import app.petclinic.domain.models.AnimalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid, Long> {

    AnimalAid findOneByName(String name);
}