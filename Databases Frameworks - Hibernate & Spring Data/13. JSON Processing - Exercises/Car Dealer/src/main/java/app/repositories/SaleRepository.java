package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {


}