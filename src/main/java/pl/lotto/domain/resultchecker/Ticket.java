package pl.lotto.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
record Ticket(
        String hash,
        Set<Integer> numbers,
        LocalDateTime drawDate
) {
}
