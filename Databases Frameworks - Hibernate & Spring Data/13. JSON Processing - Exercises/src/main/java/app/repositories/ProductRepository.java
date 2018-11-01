package app.repositories;

import app.models.dtos.view.ProductsInRangeDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value =
            "SELECT new app.models.dtos.view.ProductsInRangeDto(p.name, p.price, concat(b.firstName, ' ', b.lastName)) " +
            "FROM Product AS p " +
            "JOIN p.seller AS b " +
            "WHERE p.buyer IS NULL AND p.price BETWEEN :lowerBound AND :upperBound " +
            "ORDER BY p.price")
    List<ProductsInRangeDto> productsInRange(@Param(value = "lowerBound") BigDecimal lower, @Param(value = "upperBound") BigDecimal upper);
}