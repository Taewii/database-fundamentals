package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(value = "SELECT * FROM customers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Customer getRandomCustomer();
}