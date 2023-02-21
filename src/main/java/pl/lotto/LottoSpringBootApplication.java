package pl.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.lotto.domain.numbergenerator.WinningNumbersGeneratorFacadeConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumbersGeneratorFacadeConfigurationProperties.class})
@EnableScheduling
public class LottoSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }
}
