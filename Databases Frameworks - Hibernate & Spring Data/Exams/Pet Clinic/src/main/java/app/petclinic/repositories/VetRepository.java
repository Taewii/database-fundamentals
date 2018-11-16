package app.petclinic.repositories;

import app.petclinic.domain.models.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Vet findOneByName(String name);
}