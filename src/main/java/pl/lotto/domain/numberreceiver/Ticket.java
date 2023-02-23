package pl.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
record Ticket(
        String hash,
        Set<Integer> numbers,
        LocalDateTime drawDate) {
}
