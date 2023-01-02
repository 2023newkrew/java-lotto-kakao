package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTicketTest {

    @DisplayName("로또 티켓 번호 확인")
    @ParameterizedTest
    @MethodSource("getCheckStringTypeOfLottoNumbersData")
    public void check_string_type_of_lotto_numbers(List<Integer> numbers, String expected) {
        LottoTicket lottoTicket = new LottoTicket(numbers);
        Assertions.assertThat(lottoTicket.getString()).isEqualTo(expected);
    }

    private static Stream<Arguments> getCheckStringTypeOfLottoNumbersData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), "[1, 2, 3, 4, 5, 6]"),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43), "[3, 6, 12, 21, 41, 43]")
        );
    }
}
