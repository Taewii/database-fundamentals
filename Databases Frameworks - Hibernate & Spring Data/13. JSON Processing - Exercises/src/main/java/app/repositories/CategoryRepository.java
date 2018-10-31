package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Category getRandomCategory();
}