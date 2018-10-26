package gamestore.repositories;

import gamestore.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameById(Long id);

    Game findGameByTitle(String title);
}
