package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "SELECT * FROM suppliers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Supplier getRandomSupplier();

    @Query(value = "SELECT s FROM Supplier AS s WHERE s.importer = false")
    List<Supplier> getLocalSuppliers();
}