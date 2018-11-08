package app.repositories;

import app.models.dtos.view.categories.CategoriesByProductsDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Category getRandomCategory();

    @Query(value =
            "SELECT new app.models.dtos.view.CategoriesByProductsDto(c.name, c.products.size, AVG (p.price), SUM (p.price)) " +
            "FROM Category AS c " +
            "JOIN c.products AS p  " +
            "GROUP BY c.id " +
            "ORDER BY c.products.size DESC")
    List<CategoriesByProductsDto> categoriesByProductCount();
}