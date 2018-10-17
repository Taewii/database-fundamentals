package account_system;

import account_system.models.Account;
import account_system.models.User;
import account_system.services.account.AccountService;
import account_system.services.account.AccountServiceImpl;
import account_system.services.user.UserService;
import account_system.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... strings) {
        User user = new User();
        user.setUsername("example");
        user.setAge(23);

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(25000));
        account.setUser(user);

        user.getAccounts().add(account);

        this.userService.registerUser(user);

        this.accountService.withdrawMoney(BigDecimal.valueOf(20000), user.getId());
        this.accountService.transferMoney(BigDecimal.valueOf(10000), user.getId());
    }
}
