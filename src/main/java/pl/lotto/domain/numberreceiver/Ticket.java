package pl.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
record Ticket(String hash, Set<Integer> numbers, LocalDateTime drawDate) {
}
