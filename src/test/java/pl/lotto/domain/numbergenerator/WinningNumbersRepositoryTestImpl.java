package pl.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class WinningNumbersRepositoryTestImpl implements WinningNumbersRepository {

    private final Map<LocalDateTime, WinningNumbers> winningNumbersList = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        return winningNumbersList.put(winningNumbers.date(), winningNumbers);
    }

    @Override
    public Optional<WinningNumbers> findNumbersByDate(LocalDateTime date) {
        return Optional.ofNullable(winningNumbersList.get(date));
    }

    @Override
    public boolean existsByDate(LocalDateTime nextDrawDate) {
        winningNumbersList.get(nextDrawDate);
        return true;
    }
}
