package pl.lotto.domain.numbergenerator;

import java.util.Set;
import lombok.Builder;

@Builder
public record SixRandomNumbersDto(Set<Integer> numbers) {
}
