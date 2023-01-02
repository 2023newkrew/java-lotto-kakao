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
    @Test
    @DisplayName("정상 리스트가 인풋으로 들어왔을 때, 정상적으로 LottoTicket 생성")
    public void validateNormalLottoNumberTest() {
        //given
        List<Integer> input = List.of(1,2,3,4,5,6);

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
        List<Integer> input = List.of(1,2,3,4,5,6);
        LottoTicket lottoTicket = new LottoTicket(input);

        // when & then
        assertThat(lottoTicket.contains(1)).isEqualTo(true);
        assertThat(lottoTicket.contains(7)).isEqualTo(false);
    }

}
