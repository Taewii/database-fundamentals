package gamestore.services.user;

import gamestore.models.dtos.LoginUserDto;
import gamestore.models.dtos.RegisterUserDto;
import gamestore.models.entities.User;

public interface UserService {
    String registerUser(RegisterUserDto userDto);

    User login(LoginUserDto loginUserDto);
}
