package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoBallTest {
    @Test
    @DisplayName("로또볼은 1 ~ 45 범위의 숫자여야 한다.")
    void createLottoBall() {
        assertDoesNotThrow(() -> new LottoBall(1));
    }

    @Test
    @DisplayName("로또볼 생성 시 1 ~ 45 범위 밖의 숫자에서는 예외가 발생한다.")
    void invalidLottoNumber() {
        assertThrows(IllegalArgumentException.class, () -> new LottoBall(0));
    }
}
