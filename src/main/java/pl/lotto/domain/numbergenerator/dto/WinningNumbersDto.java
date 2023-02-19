package pl.lotto.domain.numbergenerator.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WinningNumbersDto {

    private Set<Integer> winningNumbers;
    private LocalDateTime date;
}
