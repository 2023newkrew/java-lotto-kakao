package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import lotto.model.LottoRank;
import lotto.model.LottoTicket;
import lotto.model.LottoWinningNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoWinningNumberTest {
    private final List<LottoNumber> input = createLottoNumberList(List.of(1,2,3,4,5,6));
    @Test
    public void correctBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertDoesNotThrow(() -> new LottoWinningNumber(lottoTicket, new LottoNumber(7)));
    }

    @Test
    public void wrongBonusBall() {
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThatThrownBy(() -> new LottoWinningNumber(lottoTicket, new LottoNumber(1)))
                .isInstanceOf(LottoException.class)
                .hasMessage(ErrorCode.INVALID_BONUS_BALL.getMessage());
    }

    @ParameterizedTest
    @MethodSource("checkRankTestGenerator")
    public void checkRankTest(LottoWinningNumber lottoWinningNumber, LottoRank expected){
        //given
        LottoTicket lottoTicket = new LottoTicket(input);

        //when & then
        assertThat(lottoWinningNumber.checkRank(lottoTicket)).isEqualTo(expected);
    }
    private static Stream<Arguments> checkRankTestGenerator(){
        return Stream.of(
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,5,6))), new LottoNumber(7)),
                        LottoRank.RANK1
                ),
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,5,8))), new LottoNumber(6)),
                        LottoRank.RANK2)
                ,
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,5,8))), new LottoNumber(7)),
                        LottoRank.RANK3
                ),
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,3,4,8,9))), new LottoNumber(7)),
                        LottoRank.RANK4
                ),
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,3,8,9,10))), new LottoNumber(7)),
                        LottoRank.RANK5
                ),
                Arguments.of(
                        new LottoWinningNumber(new LottoTicket(createLottoNumberList(List.of(1,2,8,9,10,11))), new LottoNumber(7)),
                        LottoRank.RANK6
                )
        );
    }

    private static List<LottoNumber> createLottoNumberList(List<Integer> integerList){
        return integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
