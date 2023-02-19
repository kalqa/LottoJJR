package pl.lotto.domain.resultannouncer;

enum MessageResponse {

    HASH_DOES_NOT_EXIST_MESSAGE("Given ticket does not exist"),
    WAIT_MESSAGE("Results are being calculated, please come back later"),
    WIN_MESSAGE("Congratulations, you won!"),
    LOSE_MESSAGE("No luck, try again!"),
    ALREADY_CHECKED("You have already checked your ticket, please come back later");

    final String info;

    MessageResponse(String info) {
        this.info = info;
    }
}
