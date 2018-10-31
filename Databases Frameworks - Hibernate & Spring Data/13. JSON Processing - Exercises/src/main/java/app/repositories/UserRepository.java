package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM users ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User getRandomUser();
}