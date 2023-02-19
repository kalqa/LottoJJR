package pl.lotto.domain.numbergenerator;

import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomOneNumberFetcher implements OneRandomNumberFetcher {

    @Override
    public OneRandomNumberResponseDto retrieveOneRandomNumber(int lowerBand, int upperBand) {
        Random random = new SecureRandom();
        return OneRandomNumberResponseDto.builder()
                .number(random.nextInt((upperBand - lowerBand) + 1))
                .build();
    }
}
