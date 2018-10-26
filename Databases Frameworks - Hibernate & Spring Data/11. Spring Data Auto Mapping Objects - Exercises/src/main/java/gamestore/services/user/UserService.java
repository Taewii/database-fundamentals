package gamestore.services.user;

import gamestore.models.dtos.binding.LoginUserDto;
import gamestore.models.dtos.binding.RegisterUserDto;
import gamestore.models.entities.Game;
import gamestore.models.entities.User;

public interface UserService {
    String registerUser(RegisterUserDto userDto);

    User login(LoginUserDto loginUserDto);

    String addGame(User user, Game game);

    String removeGame(User user, Game game);

    String buyGames(User user);
}
