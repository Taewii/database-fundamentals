package gamestore.services.game;

import gamestore.models.dtos.binding.AddGameDto;
import gamestore.models.entities.Game;
import gamestore.models.entities.User;

public interface GameService {
    String add(AddGameDto addGame);

    Game getById(long parseLong);

    String update(Game game, String... tokens);

    String delete(Game game);

    String viewAll();

    String viewDetails(String title);

    Game getGameByTitle(String title);

    String viewOwnedGames(User user);
}
