package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record WinningNumbers(
        @Id
        String id,
        Set<Integer> winningNumbers,
        LocalDateTime date) {
}
