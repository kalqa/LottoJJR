package pl.lotto.domain.numberreceiver;

import java.time.Clock;

public class NumberReceiverConfiguration {


    NumberReceiverFacade createForTest(HashGenerable hashGenerator, Clock clock, TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        return new NumberReceiverFacade(numberValidator, drawDateGenerator, hashGenerator, ticketRepository);
    }


}
