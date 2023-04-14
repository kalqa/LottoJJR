package pl.lotto.domain.resultannouncer.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;

@Builder
public record ResponseDto(
        String hash,
        Set<Integer> numbers,
        Set<Integer> hitNumbers,
        Set<Integer> wonNumbers,
        LocalDateTime drawDate,
        boolean isWinner) {
}
