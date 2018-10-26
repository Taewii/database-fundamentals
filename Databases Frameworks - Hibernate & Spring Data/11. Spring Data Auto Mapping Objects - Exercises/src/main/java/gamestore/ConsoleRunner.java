package gamestore;

import gamestore.models.dtos.binding.AddGameDto;
import gamestore.models.dtos.binding.LoginUserDto;
import gamestore.models.dtos.binding.RegisterUserDto;
import gamestore.models.entities.Game;
import gamestore.models.entities.User;
import gamestore.models.enums.Role;
import gamestore.services.game.GameService;
import gamestore.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static java.lang.System.in;

@Controller
public class ConsoleRunner implements CommandLineRunner {

    private final GameService gameService;
    private final UserService userService;
    private User loggedInUser;

    @Autowired
    public ConsoleRunner(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String input;
        while (!"end".equals(input = reader.readLine())) {
            String[] tokens = input.split("\\|");
            String command = tokens[0];
            String result = "";
            User user;
            Game game;

            switch (command) {
                case "RegisterUser":
                    RegisterUserDto registerUserDto = new RegisterUserDto(tokens[1], tokens[2], tokens[3], tokens[4]);
                    result = this.userService.registerUser(registerUserDto);
                    break;
                case "LoginUser":
                    LoginUserDto loginUserDto = new LoginUserDto(tokens[1], tokens[2]);
                    user = this.userService.login(loginUserDto);
                    if (user != null) {
                        this.setLoggedInUser(user);
                        result = String.format("Successfully logged in %s", user.getFullName());
                    } else {
                        result = "Incorrect username/password";
                    }
                    break;
                case "Logout":
                    if (this.getLoggedInUser() != null) {
                        result = String.format("User %s successfully logged out", this.getLoggedInUser().getFullName());
                        this.setLoggedInUser(null);
                    } else {
                        result = "Cannot log out. No user was logged in.";
                    }
                    break;
                case "AddGame":
                    if (this.getLoggedInUser() != null) {
                        if (this.getLoggedInUser().getRole().equals(Role.ADMIN)) {
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(tokens[7]);
                            AddGameDto addGame = new AddGameDto(tokens[1], new BigDecimal(tokens[2]),
                                    Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], date);
                            result = this.gameService.add(addGame);

                        } else {
                            result = "User doesn't have Admin privileges";
                        }
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "EditGame":
                    if (this.getLoggedInUser() != null) {
                        if (this.getLoggedInUser().getRole().equals(Role.ADMIN)) {
                            game = this.gameService.getById(Long.parseLong(tokens[1]));
                            if (game != null) {
                                result = this.gameService.update(game, Arrays.stream(tokens).skip(2).toArray(String[]::new));
                            } else {
                                result = "Game doesn't exist";
                            }
                        } else {
                            result = "User doesn't have Admin privileges";
                        }
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "DeleteGame":
                    if (this.getLoggedInUser() != null) {
                        if (this.getLoggedInUser().getRole().equals(Role.ADMIN)) {
                            game = this.gameService.getById(Long.parseLong(tokens[1]));
                            if (game != null) {
                                result = this.gameService.delete(game);
                            } else {
                                result = "Game doesn't exist";
                            }
                        } else {
                            result = "User doesn't have Admin privileges";
                        }
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "AllGame":
                    result = this.gameService.viewAll();
                    break;
                case "DetailGame":
                    result = this.gameService.viewDetails(tokens[1]);
                    break;
                case "OwnedGame":
                    user = this.getLoggedInUser();
                    if (user != null) {
                        result = this.gameService.viewOwnedGames(user);
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "AddItem":
                    game = this.gameService.getGameByTitle(tokens[1]);
                    user = this.getLoggedInUser();
                    if (user != null) {
                        if (game != null) {
                            result = this.userService.addGame(user, game);
                        } else {
                            result = "Game with such title doesn't exist";
                        }
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "RemoveItem":
                    game = this.gameService.getGameByTitle(tokens[1]);
                    user = this.getLoggedInUser();
                    if (user != null) {
                        if (game != null) {
                            result = this.userService.removeGame(user, game);
                        } else {
                            result = "Game with such title doesn't exist";
                        }
                    } else {
                        result = "No user is logged in";
                    }
                    break;
                case "BuyItem":
                    user = this.getLoggedInUser();
                    if (user != null) {
                        result = this.userService.buyGames(user);
                    } else {
                        result = "No user is logged in";
                    }
            }
            System.out.println(result);
        }
    }

    private void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    private User getLoggedInUser() {
        return this.loggedInUser;
    }
}
