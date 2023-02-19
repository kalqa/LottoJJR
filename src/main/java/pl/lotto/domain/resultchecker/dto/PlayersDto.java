package pl.lotto.domain.resultchecker.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record PlayersDto(
        List<ResultDto> results,
        String message
) {
}
