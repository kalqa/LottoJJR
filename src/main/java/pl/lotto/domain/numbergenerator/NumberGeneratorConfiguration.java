package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.domain.numberreceiver.NumberReceiverFacade;

@Configuration
public class NumberGeneratorConfiguration {

    @Bean
    WinningNumbersRepository repository() {
        return new WinningNumbersRepository() {
            @Override
            public Optional<WinningNumbers> findNumbersByDate(LocalDateTime date) {
                return Optional.empty();
            }

            @Override
            public boolean existsByDate(LocalDateTime nextDrawDate) {
                return false;
            }

            @Override
            public WinningNumbers save(WinningNumbers winningNumbers) {
                return null;
            }
        };
    }

    @Bean
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade, RandomNumberGenerable randomNumberGenerator, WinningNumbersGeneratorFacadeConfigurationProperties properties) {
        WinningNumberValidator winningNumberValidator = new WinningNumberValidator();
        return new WinningNumbersGeneratorFacade(randomNumberGenerator, winningNumberValidator, winningNumbersRepository, numberReceiverFacade, properties);
    }

    WinningNumbersGeneratorFacade createForTest(RandomNumberGenerable generator, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        WinningNumbersGeneratorFacadeConfigurationProperties properties = WinningNumbersGeneratorFacadeConfigurationProperties.builder()
                .upperBand(99)
                .lowerBand(1)
                .count(6)
                .build();
        return winningNumbersGeneratorFacade(winningNumbersRepository, numberReceiverFacade, generator, properties);
    }
}
