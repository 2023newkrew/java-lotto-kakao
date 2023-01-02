import domain.LottoBall;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoBallTest {
    @Test
    void LottoBall을_번호를_입력하여_만들_수_있다() {
        //given
        LottoBall lottoBall = new LottoBall(1);

        //when
        int number = lottoBall.getNumber();

        //then
        assertThat(number).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 18, 45})
    void LottoBall의_number값은_1부터_45_사이이다(final int number) {
        assertDoesNotThrow(() -> new LottoBall(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {46, -1, 0})
    void LottoBall의_number값은_1부터_45_사이이다_실패(final int number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new LottoBall(number));
    }

    @Test
    void LottoBall의_번호가_같다면_동등한_객체로_취급한다() {
        // given
        LottoBall lottoBall1 = new LottoBall(1);
        LottoBall lottoBall2 = new LottoBall(1);

        // when & then
        assertThat(lottoBall1.equals(lottoBall2)).isTrue();
    }
}
