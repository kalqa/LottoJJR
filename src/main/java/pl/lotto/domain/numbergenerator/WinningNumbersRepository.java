package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface WinningNumbersRepository extends Repository<WinningNumbers, String> {
    Optional<WinningNumbers> findNumbersByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);

    WinningNumbers save(WinningNumbers winningNumbers);
}
