package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User getRandomUser();

    @Query(value =
            "SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName, u.firstName")
    List<User> usersWithAtleastOneProductSold();

}