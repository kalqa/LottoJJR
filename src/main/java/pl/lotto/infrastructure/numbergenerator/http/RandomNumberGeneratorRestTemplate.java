package pl.lotto.infrastructure.numbergenerator.http;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import pl.lotto.domain.numbergenerator.RandomNumberGenerable;
import pl.lotto.domain.numbergenerator.SixRandomNumbersDto;

@AllArgsConstructor
@Log4j2
public class RandomNumberGeneratorRestTemplate implements RandomNumberGenerable {

    public static final int MAXIMAL_WINNING_NUMBERS = 6;
    public static final String RANDOM_NUMBER_SERVICE_PATH = "/api/v1.0/random";

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public SixRandomNumbersDto generateSixRandomNumbers(int count, int lowerBand, int upperBand) {
        log.info("Started fetching winning numbers using http client");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            final ResponseEntity<List<Integer>> response = makeGetRequest(count, lowerBand, upperBand, requestEntity);
            Set<Integer> sixDistinctNumbers = getSixRandomDistinctNumbers(response);
            if (sixDistinctNumbers.size() != MAXIMAL_WINNING_NUMBERS) {
                log.error("Set is less than: {} Have to request one more time", count);
                return generateSixRandomNumbers(count, lowerBand, upperBand);
            }
            return SixRandomNumbersDto.builder()
                    .numbers(sixDistinctNumbers)
                    .build();
        } catch (ResourceAccessException e) {
            log.error("Error while fetching winning numbers using http client: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<List<Integer>> makeGetRequest(int count, int lowerBand, int upperBand, HttpEntity<HttpHeaders> requestEntity) {
        final String url = UriComponentsBuilder.fromHttpUrl(getUrlForService(RANDOM_NUMBER_SERVICE_PATH))
                .queryParam("min", lowerBand)
                .queryParam("max", upperBand)
                .queryParam("count", count)
                .toUriString();
        ResponseEntity<List<Integer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return response;
    }

    private Set<Integer> getSixRandomDistinctNumbers(ResponseEntity<List<Integer>> response) {
        List<Integer> numbers = response.getBody();
        if (numbers == null) {
            log.error("Response Body was null");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        log.info("Success Response Body Returned: " + response);
        Set<Integer> distinctNumbers = new HashSet<>(numbers);
        return distinctNumbers.stream()
                .limit(6)
                .collect(Collectors.toSet());
    }


    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
