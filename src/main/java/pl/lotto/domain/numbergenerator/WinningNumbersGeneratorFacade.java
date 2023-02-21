package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import pl.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.domain.numberreceiver.NumberReceiverFacade;

@AllArgsConstructor
public class WinningNumbersGeneratorFacade {

    private final RandomNumberGenerable randomGenerable;
    private final WinningNumberValidator winningNumberValidator;
    private final WinningNumbersRepository winningNumbersRepository;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersGeneratorFacadeConfigurationProperties properties;

    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        SixRandomNumbersDto sixRandomNumbersDto = randomGenerable.generateSixRandomNumbers(properties.count(), properties.lowerBand(), properties.upperBand());
        Set<Integer> winningNumbers = sixRandomNumbersDto.numbers();
        winningNumberValidator.validate(winningNumbers);
        WinningNumbers winningNumbersDocument = WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(nextDrawDate)
                .build();
        WinningNumbers savedNumbers = winningNumbersRepository.save(winningNumbersDocument);
        return WinningNumbersDto.builder()
                .winningNumbers(savedNumbers.winningNumbers())
                .date(savedNumbers.date())
                .build();
    }

    public WinningNumbersDto retrieveWinningNumberByDate(LocalDateTime date) {
        WinningNumbers numbersByDate = winningNumbersRepository.findNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not Found"));
        return WinningNumbersDto.builder()
                .winningNumbers(numbersByDate.winningNumbers())
                .date(numbersByDate.date())
                .build();
    }

    public boolean areWinningNumbersGeneratedByDate() {
        LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();
        return winningNumbersRepository.existsByDate(nextDrawDate);
    }
}
