package pl.lotto.domain.numberreceiver;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.lotto.domain.AdjustableClock;
import pl.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import pl.lotto.domain.numberreceiver.dto.TicketDto;
import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023, 2, 15, 11, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());

    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl(),
            clock
    );

    @Test
    public void should_return_success_when_user_gave_six_numbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failed_when_user_gave_at_least_one_number_out_of_range_of_1_to_99() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2000, 3, 4, 5, 6);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_failed_when_user_gave_less_than_six_numbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        // when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    public void should_return_save_to_database_when_user_gave_six_numbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 15, 12, 0, 0);
        // when
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);
        // then
        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.ticketId())
                        .drawDate(drawDate)
                        .numbersFromUser(result.numbersFromUser())
                        .build()
        );
    }
//
//    @Test
//    public void should_return_failed_when_user_gave_more_than_six_numbers() {
//
//    }

}
