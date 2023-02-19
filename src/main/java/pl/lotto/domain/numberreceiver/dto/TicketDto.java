package pl.lotto.domain.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
public record TicketDto(LocalDateTime drawDate, String ticketId, Set<Integer> numbersFromUser) {
}
