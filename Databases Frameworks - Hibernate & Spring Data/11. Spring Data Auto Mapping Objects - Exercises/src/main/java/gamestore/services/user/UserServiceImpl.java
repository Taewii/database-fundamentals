package gamestore.services.user;

import gamestore.models.dtos.LoginUserDto;
import gamestore.models.dtos.RegisterUserDto;
import gamestore.models.entities.User;
import gamestore.models.enums.Role;
import gamestore.repositories.UserRepository;
import gamestore.models.utils.ObjectValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(RegisterUserDto userDto) {
        if (userDto.isPasswordMatch()) {
            try {
                User user = this.modelMapper.map(userDto, User.class);
                setRole(user);
                ObjectValidator.validate(user);
                this.userRepository.saveAndFlush(user);
                return String.format("%s was registered", user.getFullName());
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } else {
            return "Passwords don't match";
        }
    }

    @Override
    public User login(LoginUserDto loginUserDto) {
        User user = this.userRepository.findByEmail(loginUserDto.getEmail());

        if (user != null) {
            if (user.getPassword().equals(loginUserDto.getPassword())) {
                return user;
            }
        }
        return null;
    }

    private void setRole(User user) {
        if (this.userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
    }
}
