package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LotteryNumberTest {
    @DisplayName("1~45의 숫자를 받아 LottoNumber를 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 25, 45})
    void lotteryNumberTest_valid(int number) {
        //given
        //when
        //then
        Assertions.assertThatNoException().isThrownBy(() -> new LotteryNumber(number));
    }

    @DisplayName("0보다 작거나, 45이상인 수를 받으면 예외를 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, 47, 48})
    void lotteryNumberTest_invalid(int number) {
        //given
        //when
        //then
        Assertions.assertThatRuntimeException().isThrownBy(() -> new LotteryNumber(number));
    }
}
