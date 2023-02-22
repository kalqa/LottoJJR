package pl.lotto.infrastructure.apivalidation;

import java.util.List;
import org.springframework.http.HttpStatus;

public record ApiValidationErrorDto(
        List<String> messages,
        HttpStatus status
) { }
