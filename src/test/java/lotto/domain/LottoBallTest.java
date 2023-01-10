package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallTest {
    @Test
    @DisplayName("로또볼은 1 ~ 45 범위의 숫자여야 한다.")
    void createLottoBall() {
        assertThatCode(() -> new LottoBall(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또볼 생성 시 1 ~ 45 범위 밖의 숫자에서는 예외가 발생한다.")
    void invalidLottoNumber() {
        assertThatThrownBy(() -> new LottoBall(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ball은 1 ~ 45 사이의 숫자여야 합니다.");
    }
}
