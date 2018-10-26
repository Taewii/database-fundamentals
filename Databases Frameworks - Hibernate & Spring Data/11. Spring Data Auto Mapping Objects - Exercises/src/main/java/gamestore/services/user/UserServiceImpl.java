package gamestore.services.user;

import gamestore.models.dtos.binding.LoginUserDto;
import gamestore.models.dtos.binding.RegisterUserDto;
import gamestore.models.entities.Game;
import gamestore.models.entities.User;
import gamestore.models.enums.Role;
import gamestore.repositories.UserRepository;
import gamestore.models.utils.ObjectValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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
        User existingUser = this.userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            return "User with such email already exists.";
        }

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

    @Override
    public String addGame(User user, Game game) {
        Set<Game> shoppingCart = user.getShoppingCart();
        Set<Game> games = user.getGames();
        if (!userOwnsOrHasGameInShoppingCart(shoppingCart, game.getTitle())) {
            if (!userOwnsOrHasGameInShoppingCart(games, game.getTitle())) {
                user.getShoppingCart().add(game);
                this.userRepository.saveAndFlush(user);
                return String.format("Added %s", game.getTitle());
            } else {
                return "Game is already owned by user";
            }
        } else {
            return "Game is already in the users shopping cart";
        }
    }

    @Override
    public String removeGame(User user, Game game) {
        boolean isRemoved = user.getShoppingCart().removeIf(g -> g.getTitle().equals(game.getTitle()));
        this.userRepository.saveAndFlush(user);
        if (isRemoved) {
            return String.format("%s removed from cart.", game.getTitle());
        } else {
            return "No such game in users cart exists";
        }
    }

    @Override
    public String buyGames(User user) {
        Set<Game> shoppingCart = user.getShoppingCart();
        Set<Game> games = user.getGames();
        StringBuilder sb = new StringBuilder();
        int boughtGames = 0;

        for (Game game : shoppingCart) {
            if (!userOwnsOrHasGameInShoppingCart(games, game.getTitle())) {
                games.add(game);
                sb.append("- ").append(game.getTitle()).append(System.lineSeparator());
                boughtGames++;
            }
        }

        user.getShoppingCart().clear();
        this.userRepository.saveAndFlush(user);
        if (boughtGames > 0) {
            return String.format("Successfully bought games:%n") + sb.toString().trim();
        } else {
            return "No games were purchased";
        }
    }

    private boolean userOwnsOrHasGameInShoppingCart(Set<Game> games, String title) {
        for (Game game : games) {
            if (game.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    private void setRole(User user) {
        if (this.userRepository.count() == 0) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
    }
}
