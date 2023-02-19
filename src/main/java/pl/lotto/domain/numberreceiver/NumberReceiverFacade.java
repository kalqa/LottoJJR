package pl.lotto.domain.numberreceiver;


/*
*
*
*
* klient podaje 6 liczb
liczby muszą być w zakresie od 1-99
liczby nie mogą się powtarzać
klient dostaje informacje o dacie losowanie
klient dostaje informacje o swoim unikalnym identyfikatorze losowanie

*
* */

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import pl.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import pl.lotto.domain.numberreceiver.dto.TicketDto;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        boolean areAllNumbersInRange = validator.areAllNumbersInRange(numbersFromUser);
        if (areAllNumbersInRange) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = LocalDateTime.now(clock);
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return InputNumberResultDto.builder()
                    .drawDate(savedTicket.drawDate())
                    .ticketId(savedTicket.ticketId())
                    .numbersFromUser(numbersFromUser)
                    .message("success")
                    .build();
        }
        return InputNumberResultDto.builder()
                .message("failed")
                .build();
    }

    public List<TicketDto> userNumbers(LocalDateTime date) {
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate
                .stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }
}
