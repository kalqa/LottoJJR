package pl.lotto.infrastructure.numberreceiver.controller;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.domain.numberreceiver.NumberReceiverFacade;
import pl.lotto.domain.numberreceiver.dto.NumberReceiverResponseDto;

@RestController
@Log4j2
@AllArgsConstructor
public class InputNumbersRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    public ResponseEntity<NumberReceiverResponseDto> inputNumbers(@RequestBody InputNumbersRequestDto requestDto) {
        Set<Integer> distinctNumbers = new HashSet<>(requestDto.inputNumbers());
        NumberReceiverResponseDto numberReceiverResponseDto = numberReceiverFacade.inputNumbers(distinctNumbers);
        return ResponseEntity.ok(numberReceiverResponseDto);
    }
}
