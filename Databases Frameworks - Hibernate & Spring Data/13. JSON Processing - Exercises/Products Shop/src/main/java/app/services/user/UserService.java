package app.services.user;

import app.models.dtos.binding.UserDto;
import app.models.dtos.view.SuccessfulUserDto;
import app.models.dtos.view.query4.UsersCountAndUsersDto;

import java.util.List;

public interface UserService {
    void saveAll(UserDto[] users);

    List<SuccessfulUserDto> usersWithAtleastOneProductSold();

    UsersCountAndUsersDto userCountWithUsersAndProductCountWithProducts();
}