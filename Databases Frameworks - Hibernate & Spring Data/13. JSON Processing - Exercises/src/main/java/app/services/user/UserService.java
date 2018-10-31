package app.services.user;

import app.models.dtos.UserDto;

public interface UserService {
    void saveAll(UserDto[] users);
}