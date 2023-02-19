package pl.lotto.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;


@Builder
record Player(String hash,
              Set<Integer> numbers,
              Set<Integer> hitNumbers,
              LocalDateTime drawDate,
              boolean isWinner) {
}
