package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, String> {
    Optional<WinningNumbers> findNumbersByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime nextDrawDate);
}
