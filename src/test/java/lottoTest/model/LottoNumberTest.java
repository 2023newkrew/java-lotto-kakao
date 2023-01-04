package lottoTest.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoNumberTest {
    @ParameterizedTest
    @MethodSource("validateInvalidLottoNumberTestGenerator")
    @DisplayName("로또 번호로 유효하지 않은 값이 입력되면 예외 반환")
    public void validateInvalidLottoNumberTest(Integer input, ErrorCode expected) {
        //given

        //when & then
        assertThatThrownBy(() -> LottoNumber.numberOf(input))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());

    }

    private static Stream<Arguments> validateInvalidLottoNumberTestGenerator() {
        return Stream.of(
                Arguments.of(-1, ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(0, ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(46, ErrorCode.INVALID_LOTTO_NUMBER_RANGE)
        );
    }
}
