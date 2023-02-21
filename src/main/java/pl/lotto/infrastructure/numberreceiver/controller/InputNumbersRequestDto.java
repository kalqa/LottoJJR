package pl.lotto.infrastructure.numberreceiver.controller;

import java.util.List;

public record InputNumbersRequestDto(List<Integer> inputNumbers) {
}
