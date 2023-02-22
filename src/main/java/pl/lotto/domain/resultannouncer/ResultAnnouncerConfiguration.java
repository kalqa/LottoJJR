package pl.lotto.domain.resultannouncer;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerConfiguration {

    @Bean
    ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade, ResponseRepository responseRepository, Clock clock) {
        return new ResultAnnouncerFacade(resultCheckerFacade, responseRepository, clock);
    }
}
