package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoTicketTest {
    private final List<Integer> input = List.of(1,2,3,4,5,6);

    @Test
    @DisplayName("정상 리스트가 인풋으로 들어왔을 때, 정상적으로 LottoTicket 생성")
    public void validateNormalLottoNumberTest() {
        //given

        //when & then
        assertDoesNotThrow(() -> new LottoTicket(input));
    }
    @ParameterizedTest
    @MethodSource("validateInvalidLottoNumberTestGenerator")
    public void validateInvalidLottoNumberTest(List<Integer> input, ErrorCode expected){
        //given

        //when & then
        assertThatThrownBy(() -> new LottoTicket(input))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());

    }
    private static Stream<Arguments> validateInvalidLottoNumberTestGenerator(){
        return Stream.of(
                Arguments.of(List.of(-1, 1, 2, 3, 4, 5), ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(List.of(0, 1, 2, 3, 4, 5), ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(List.of(46, 1, 2, 3, 4, 5), ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH),
                Arguments.of(List.of(1, 2, 3, 4, 5), ErrorCode.INVALID_LOTTO_NUMBER_LENGTH),
                Arguments.of(null, ErrorCode.INVALID_LOTTO_NUMBER_LENGTH)
        );
    }

    @Test
    public void includeNumberTest() {
        // given
        LottoTicket lottoTicket = new LottoTicket(input);

        // when & then
        assertThat(lottoTicket.contains(1)).isEqualTo(true);
        assertThat(lottoTicket.contains(7)).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("countIncludedNumberTestGenerator")
    public void countIncludedNumberTest(LottoTicket other, Integer count) {
        // given
        LottoTicket lottoTicket = new LottoTicket(input);

        // when & then
        assertThat(lottoTicket.countIncludedNumber(other)).isEqualTo(count);
    }
    private static Stream<Arguments> countIncludedNumberTestGenerator(){
        return Stream.of(
                Arguments.of(new LottoTicket(List.of(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new LottoTicket(List.of(1, 2, 3, 4, 5, 7)), 5),
                Arguments.of(new LottoTicket(List.of(1, 2, 3, 4, 7, 8)), 4),
                Arguments.of(new LottoTicket(List.of(1, 2, 3, 7, 8, 9)), 3),
                Arguments.of(new LottoTicket(List.of(1, 2, 7, 8, 9, 10)), 2),
                Arguments.of(new LottoTicket(List.of(1, 7, 8, 9, 10, 11)), 1),
                Arguments.of(new LottoTicket(List.of(7, 8, 9, 10, 11, 12)), 0)
        );
    }

}
