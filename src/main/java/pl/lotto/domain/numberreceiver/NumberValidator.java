package pl.lotto.domain.numberreceiver;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class NumberValidator {

    private static final int QUANTITY_OF_NUMBERS_FROM_USER = 6;
    private static final int MAX_VALUE_NUMBER_FROM_USER = 99;
    private static final int MIN_VALUE_NUMBER_FROM_USER = 1;

    List<ValidationResult> errors;

    List<ValidationResult> validate(Set<Integer> numbersFromUser) {
        errors = new LinkedList<>();
        if (!isNumbersSizeEqualSix(numbersFromUser)) {
            errors.add(ValidationResult.NOT_SIX_NUMBERS_GIVEN);
        }
        if (!isNumberInRange(numbersFromUser)) {
            errors.add(ValidationResult.NOT_IN_RANGE);
        }
        return errors;
    }

    String createResultMessage() {
        return this.errors
                .stream()
                .map(validationResult -> validationResult.info)
                .collect(Collectors.joining(","));
    }

    private boolean isNumbersSizeEqualSix(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() == QUANTITY_OF_NUMBERS_FROM_USER;
    }

    boolean isNumberInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .allMatch(number -> number >= MIN_VALUE_NUMBER_FROM_USER && number <= MAX_VALUE_NUMBER_FROM_USER);
    }
}
