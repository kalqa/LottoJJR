package pl.lotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.lotto.infrastructure.AasdasdRepository;
import pl.lotto.infrastructure.Ticket;

@SpringBootApplication
public class LottoSpringBootApplication
        implements CommandLineRunner {

    @Autowired
    AasdasdRepository aasdasd;

    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) {
        aasdasd.save(new Ticket("asdasd"));
    }
}
