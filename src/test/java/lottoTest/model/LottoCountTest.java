package lottoTest.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.exception.ErrorCode;
import lotto.exception.LottoException;
import lotto.model.LottoCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoCountTest {
    @ParameterizedTest
    @MethodSource("validateWrongManualLottoCountTestGenerator")
    @DisplayName("수동 로또 개수에서 발생할 수 있는 예외 테스트")
    public void validateWrongManualLottoCountTest(String manualLottoCount, ErrorCode expected) {
        //given
        Integer totalLottoCount = 14;
        //when & then
        assertThatThrownBy(() -> new LottoCount(totalLottoCount, manualLottoCount))
                .isInstanceOf(LottoException.class)
                .hasMessage(expected.getMessage());
    }

    private static Stream<Arguments> validateWrongManualLottoCountTestGenerator() {
        return Stream.of(
                Arguments.of("abc", ErrorCode.INVALID_INPUT_TYPE_NOT_INTEGER),
                Arguments.of("-1", ErrorCode.INVALID_MANUAL_LOTTO_COUNT),
                Arguments.of("15", ErrorCode.LACK_OF_MONEY)
        );
    }
}
