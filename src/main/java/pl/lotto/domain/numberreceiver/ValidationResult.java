package pl.lotto.domain.numberreceiver;

enum ValidationResult {
    NOT_SIX_NUMBERS_GIVEN("YOU SHOULD GIVE 6 NUMBERS"),
    NOT_IN_RANGE("YOU SHOULD GIVE NUMBERS FROM 1 TO 99"),
    INPUT_SUCCESS("SUCCESS");

    final String info;

    ValidationResult(String info) {
        this.info = info;
    }
}
