package pl.lotto.infrastructure.resultchecker.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.domain.numbergenerator.WinningNumbersGeneratorFacade;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;
import pl.lotto.domain.resultchecker.dto.PlayersDto;

@Component
@Log4j2
@AllArgsConstructor
public class ResultCheckerScheduler {

    private final ResultCheckerFacade resultCheckerFacade;
    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Scheduled(cron = "${lotto.result-checker.lotteryRunOccurrence}")
    public PlayersDto generateWinners() {
        log.info("ResultCheckerScheduler started...");
        if (!winningNumbersGeneratorFacade.areWinningNumbersGeneratedByDate()) {
            log.error("Winning numbers are not generated");
            throw new RuntimeException("Winning numbers are not generated");
        }
        log.info("Winning numbers has been fetched");
        return resultCheckerFacade.generateResults();
    }
}
