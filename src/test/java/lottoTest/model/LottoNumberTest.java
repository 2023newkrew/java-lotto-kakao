package lottoTest.model;

import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @MethodSource("validateInvalidLottoNumberTestGenerator")
    public void validateInvalidLottoNumberTest(Integer input, ErrorCode expected){
        //given

        //when & then
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());

    }
    private static Stream<Arguments> validateInvalidLottoNumberTestGenerator(){
        return Stream.of(
                Arguments.of(-1, ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(0, ErrorCode.INVALID_LOTTO_NUMBER_RANGE),
                Arguments.of(46, ErrorCode.INVALID_LOTTO_NUMBER_RANGE)
        );
    }
}
