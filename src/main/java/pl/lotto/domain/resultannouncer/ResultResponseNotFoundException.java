package pl.lotto.domain.resultannouncer;

public class ResultResponseNotFoundException extends RuntimeException {

    ResultResponseNotFoundException(String message) {
        super(message);
    }
}
