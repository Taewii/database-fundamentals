package account_system.services.user;

import account_system.models.User;
import account_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Long id = user.getId();

        if (id != null) {
            if (this.userRepository.exists(id)) {
                throw new IllegalArgumentException("Id already exists");
            }
        }

        String username = user.getUsername();

        if (username != null) {
            User userByUsername = this.userRepository.findByUsername(username);

            if (userByUsername != null) {
                throw new IllegalArgumentException("Username already exists");
            }

        }

        this.userRepository.save(user);
    }
}
