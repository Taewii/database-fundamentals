package app.petclinic.repositories;

import app.petclinic.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {

    Passport findOneBySerialNumber(String serialNumber);
}