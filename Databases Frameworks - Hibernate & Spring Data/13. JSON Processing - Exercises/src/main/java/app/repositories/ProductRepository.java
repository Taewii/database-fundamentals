package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}