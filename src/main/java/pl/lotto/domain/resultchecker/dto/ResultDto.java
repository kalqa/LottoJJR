package pl.lotto.domain.resultchecker.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
public record ResultDto(String hash,
                        Set<Integer> numbers,
                        Set<Integer> hitNumbers,
                        LocalDateTime drawDate,
                        boolean isWinner
) {
}
