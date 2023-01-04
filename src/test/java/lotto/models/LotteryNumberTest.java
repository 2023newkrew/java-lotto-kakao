package lotto.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LotteryNumberTest {

    static public final Integer MIN_VALUE = 1;

    static public final Integer MAX_VALUE = 45;

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 35, 45})
    @DisplayName("1~45 사이의 로또넘버를 가져올 수 있다.")
    public void testValidLotteryNumber(Integer input) {
        LotteryNumber lotteryNumber = LotteryNumber.from(input);
        Assertions.assertThat(lotteryNumber).isSameAs(LotteryNumber.from(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -45, -46, 46, 9999999})
    @DisplayName("1 ~ 45 이외의 로또넘버를 가져오려하면 예외가 발생한다.")
    public void testInvalidLotteryNumber(Integer input) {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> LotteryNumber.from(input))
                .withMessage("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
    }
}