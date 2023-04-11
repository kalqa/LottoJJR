package pl.lotto.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import org.springframework.data.annotation.Id;


@Builder
record Player(
        @Id
        String hash,
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        Set<Integer> wonNumbers
        ) {
}
