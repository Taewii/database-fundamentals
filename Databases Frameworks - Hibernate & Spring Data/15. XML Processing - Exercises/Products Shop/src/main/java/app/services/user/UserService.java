package app.services.user;

import app.models.dtos.binding.UserDto;
import app.models.dtos.view.users.SuccessfulUserDto;
import app.models.dtos.view.users.UsersCountAndUsersDto;
import app.models.entities.User;

import java.util.List;

public interface UserService {
    void saveAll(UserDto[] users);

    List<SuccessfulUserDto> usersWithAtleastOneProductSoldDto();

    UsersCountAndUsersDto userCountWithUsersAndProductCountWithProducts();

    List<User> usersWithAtleastOneProductSold();
}