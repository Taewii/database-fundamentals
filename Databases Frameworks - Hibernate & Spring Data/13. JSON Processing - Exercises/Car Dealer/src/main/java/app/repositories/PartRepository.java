package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Part;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {

    @Query(value = "SELECT * FROM parts ORDER BY RAND() LIMIT 20", nativeQuery = true)
    List<Part> get20RandomParts();
}