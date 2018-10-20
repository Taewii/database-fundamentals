package app.repositories;

import app.models.enums.Size;
import app.models.labels.BasicLabel;
import app.models.shampoos.BasicShampoo;
import app.models.shampoos.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<BasicShampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(final Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, BasicLabel label);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    int countAllByPriceBefore(BigDecimal price);

    @Query(value =
            "SELECT s FROM BasicShampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name IN :ingredients")
    List<Shampoo> shampoosWithIngredientsIn(@Param(value = "ingredients") List<String> ingredients);

    @Query(value =
            "SELECT s FROM BasicShampoo AS s\n" +
            "WHERE s.ingredients.size < :size")
    List<Shampoo> shampoosWithIngredientsSizeLessThan(@Param(value = "size") int size);

    @Query(value =
            "SELECT SUM(i.price)\n" +
            "FROM BasicShampoo AS s\n" +
            "JOIN s.ingredients AS i\n" +
            "WHERE s.brand = :brand")
    BigDecimal priceOfIngredientsOfShampoo(@Param(value = "brand") String brand);
}
