package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import lotto.domain.WinningLotto;
import lotto.domain.enumeration.LottoResult;
import lotto.domain.LottoTicket;
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
        Assertions.assertThat(lottoTicket.toString()).isEqualTo(expected);
    }

    private static Stream<Arguments> getCheckStringTypeOfLottoNumbersData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                        "[1, 2, 3, 4, 5, 6]"),
                Arguments.of(Arrays.asList(3, 6, 12, 21, 41, 43),
                        "[3, 6, 12, 21, 41, 43]")
        );
    }

    @DisplayName("당첨 결과 계산")
    @ParameterizedTest
    @MethodSource("getCalculateWinningResultData")
    public void calculate_winning_result(List<Integer> numbers, List<Integer> winningNumbers,
            int bonusNumber, LottoResult result) {
        LottoTicket lottoTicket = new LottoTicket(numbers);
        WinningLotto winningLotto
                = new WinningLotto(winningNumbers, bonusNumber);
        Assertions.assertThat(lottoTicket.getResult(winningLotto)).isEqualTo(result);
    }

    private static Stream<Arguments> getCalculateWinningResultData() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6),
                        Arrays.asList(1, 2, 3, 4, 5, 6), 7, LottoResult.FIRST),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7),
                        Arrays.asList(1, 2, 3, 4, 5, 6), 7, LottoResult.SECOND),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 8),
                        Arrays.asList(1, 2, 3, 4, 5, 6), 7, LottoResult.THIRD),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 8, 9),
                        Arrays.asList(1, 2, 3, 4, 5, 6), 7, LottoResult.FOURTH),
                Arguments.of(Arrays.asList(1, 2, 3, 15, 25, 41),
                        Arrays.asList(1, 2, 3, 4, 5, 6), 41, LottoResult.FIFTH)
        );
    }


}
