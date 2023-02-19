package pl.lotto.domain.resultannouncer;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
record ResultResponse(
        String hash,
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner) {
}
