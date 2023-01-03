package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LotteryNumberTest {
    @DisplayName("1~45인 수로 생성 성공")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25, 45})
    void lotteryNumberTest_valid(int number) {
        //given
        //when
        //then
        Assertions.assertThatNoException().isThrownBy(() -> new LotteryNumber(number));
    }

    @DisplayName("0, 45이상인 수로 생성시 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 47, 48})
    void lotteryNumberTest_invalid(int number) {
        //given
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> new LotteryNumber(number));
    }
}
