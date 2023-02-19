package pl.lotto.domain.resultannouncer;

import java.time.Clock;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerConfiguration {

    ResultAnnouncerFacade createForTest(ResultCheckerFacade resultCheckerFacade, ResponseRepository responseRepository, Clock clock) {
        return new ResultAnnouncerFacade(resultCheckerFacade, responseRepository, clock);
    }
}
