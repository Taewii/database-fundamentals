package gamestore.services.game;

import gamestore.models.dtos.AddGameDto;
import gamestore.models.entities.Game;

public interface GameService {
    String add(AddGameDto addGame);

    Game getById(long parseLong);

    String update(Game game, String... tokens);

    String delete(Game game);
}
