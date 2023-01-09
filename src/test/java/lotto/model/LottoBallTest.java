package lotto.model;

import lotto.exception.InvalidLottoBallNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoBallTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 45, 24, 25})
    void 정상_실행_1에서_45_사이일_때는(int num) {
        assertThatCode(() -> {
            LottoBall lottoBall = new LottoBall(num);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, 4999, -1, -122})
    void 예외_던짐_1에서_45_사이가_아닐때(int num) {
        assertThatThrownBy(() -> {
            LottoBall lottoBall = new LottoBall(num);
        }).isInstanceOf(InvalidLottoBallNumber.class);
    }

    @Test
    void 비교_가능_해야_함() {
        LottoBall ballNum1 = new LottoBall(1);
        LottoBall ballNum2 = new LottoBall(5);
        Assertions.assertThat(ballNum1.compareTo(ballNum2) < 0).isTrue();
        Assertions.assertThat(ballNum2.compareTo(ballNum1) > 0).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 45, 24, 25})
    void toString_정상작동(int val) {
        LottoBall ballNum1 = new LottoBall(val);
        Assertions.assertThat(ballNum1.toString()).isEqualTo("" + val);
    }
}