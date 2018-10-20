package app.repositories;

import app.models.ingredients.BasicIngredient;
import app.models.ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<BasicIngredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String str);

    List<Ingredient> findAllByNameIn(List<String> names);

    @Modifying
    @Transactional
    void deleteIngredientByName(String ingredientName);

    @Modifying
    @Transactional
    @Query("UPDATE BasicIngredient AS b SET b.price = b.price * 1.1")
    void increaseAllIngredientsPriceBy10Percents();

    @Modifying
    @Transactional
    @Query("UPDATE BasicIngredient AS b SET b.price = b.price * 1.1 WHERE b.name IN :names")
    void increaseIngredientsPriceBy10PercentsFromList(@Param("names") List<String> ingredientNames);
}
