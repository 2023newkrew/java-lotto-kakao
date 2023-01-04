package lottoTest.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import lotto.model.LottoRank;
import lotto.model.LottoTicket;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoWinningNumberTest {
    private final List<LottoNumber> input = createLottoNumberList(List.of(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("보너스 번호가 winningNumber에 포함되어 있지 않은 경우")
    public void correctBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertDoesNotThrow(() -> new LottoWinningNumber(lottoTicket, LottoNumber.from(7)));
    }

    @Test
    @DisplayName("보너스 번호가 winningNumber에 포함되어 있는 경우")
    public void wrongBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThatThrownBy(() -> new LottoWinningNumber(lottoTicket, LottoNumber.from(1)))
                .isInstanceOf(LottoException.class)
                .hasMessage(ErrorCode.INVALID_BONUS_BALL.getMessage());
    }

    @ParameterizedTest
    @MethodSource("checkRankTestGenerator")
    @DisplayName("로또 티켓의 등수 구하기")
    public void checkRankTest(LottoWinningNumber lottoWinningNumber, LottoRank expected) {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThat(lottoWinningNumber.checkLottoRank(lottoTicket)).isEqualTo(expected);
    }

    private static Stream<Arguments> checkRankTestGenerator() {
        return Stream.of(
                Arguments.of(new LottoWinningNumber("1,2,3,4,5,6", "7"), LottoRank.RANK1),
                Arguments.of(new LottoWinningNumber("1,2,3,4,5,8", "6"), LottoRank.RANK2),
                Arguments.of(new LottoWinningNumber("1,2,3,4,5,8", "7"), LottoRank.RANK3),
                Arguments.of(new LottoWinningNumber("1,2,3,4,8,9", "7"), LottoRank.RANK4),
                Arguments.of(new LottoWinningNumber("1,2,3,8,9,10", "7"), LottoRank.RANK5),
                Arguments.of(new LottoWinningNumber("1,2,8,9,10,11", "7"), LottoRank.RANK6)
        );
    }

    private static List<LottoNumber> createLottoNumberList(List<Integer> integerList) {
        return integerList.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList());
    }
}
