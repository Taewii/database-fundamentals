package gamestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
