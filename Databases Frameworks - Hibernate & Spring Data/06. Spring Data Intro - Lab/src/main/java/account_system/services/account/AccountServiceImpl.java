package account_system.services.account;

import account_system.models.Account;
import account_system.models.User;
import account_system.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.findOne(id);

        if (account == null) {
            throw new IllegalArgumentException("No account with such ID exists");
        }

        User user = account.getUser();

        if (user == null) {
            throw new IllegalArgumentException("Account doesn't belong to an user");
        }

//        setter checks for this
//        if (account.getBalance().compareTo(money) < 0) {
//            throw new IllegalArgumentException("Balance is not enough");
//        }

        BigDecimal newBalance = account.getBalance().subtract(money);
        account.setBalance(newBalance);
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = accountRepository.findOne(id);

        if (account == null) {
            throw new IllegalArgumentException("No account with such ID exists");
        }

        if (account.getUser() == null) {
            throw new IllegalArgumentException("Account doesn't belong to an user");
        }

        if (BigDecimal.ZERO.compareTo(money) > 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }

        BigDecimal newBalance = account.getBalance().add(money);
        account.setBalance(newBalance);
        this.accountRepository.save(account);
    }
}
