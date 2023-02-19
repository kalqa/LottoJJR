package pl.lotto.domain.numberreceiver;

import java.util.Set;

// encja do bazy
record Ticket(String ticketId, java.time.LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
