package pl.lotto.domain.numbergenerator;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomGenerator implements RandomNumberGenerable {

    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;

    private final int RANDOM_NUMBER_BOUND = (UPPER_BAND - LOWER_BAND) + 1;

    public Set<Integer> generateSixRandomNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        while (isAmountOfNumbersLowerThanSix(winningNumbers)) {
            int randomNumber = generateRandom();
            winningNumbers.add(randomNumber);
        }
        return winningNumbers;
    }

    private boolean isAmountOfNumbersLowerThanSix(Set<Integer> winningNumbers) {
        return winningNumbers.size() < 6;
    }

    private int generateRandom() {
        Random random = new SecureRandom();
        return random.nextInt(RANDOM_NUMBER_BOUND) +1;
    }
}
