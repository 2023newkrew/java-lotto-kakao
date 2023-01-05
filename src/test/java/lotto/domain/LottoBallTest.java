package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoBallTest {

    @DisplayName("LottoBall을 번호를 입력하여 만들 수 있다")
    @Test
    void createLottoBall() {
        //given
        LottoBall lottoBall = new LottoBall(1);

        //when
        int number = lottoBall.getNumber();

        //then
        assertThat(number).isEqualTo(1);
    }

    @DisplayName("LottoBall의 number값은 1부터 45 사이이다")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 18, 45})
    void createLottoBallValidNumber(final int number) {
        assertDoesNotThrow(() -> new LottoBall(number));
    }

    @DisplayName("LottoBall의 number값은 1부터 45 사이가 아니면 예외가 던져진다.")
    @ParameterizedTest
    @ValueSource(ints = {46, -1, 0})
    void createInvalidLottoBall(final int number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoBall(number))
                .withMessageContaining("사이의 숫자만을 허용합니다.");
    }

    @DisplayName("LottoBall의 번호가 같다면 동등한 객체로 취급한다")
    @Test
    void testEquality() {
        // given
        LottoBall lottoBall1 = new LottoBall(1);
        LottoBall lottoBall2 = new LottoBall(1);

        // when & then
        assertThat(lottoBall1.equals(lottoBall2)).isTrue();
    }

    @DisplayName("LottoBall을 비교할 수 있다.")
    @Test
    void compareLottoBall() {
        // given
        LottoBall lottoBall1 = new LottoBall(1);
        LottoBall lottoBall2 = new LottoBall(2);

        // when & then
        assertThat(lottoBall1.compareTo(lottoBall2)).isLessThan(0);
    }
}
