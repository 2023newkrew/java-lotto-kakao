package lotto;

import lotto.model.Lotto;
import lotto.model.errors.LottoDuplicatedNumberException;
import lotto.model.errors.LottoOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    @Test
    @DisplayName("로또 길이 테스트")
    void lotto_length_test() {
        try {
            Lotto.of(1, 2, 3, 4, 5);
        } catch (Exception e) {
            assertTrue(e instanceof LottoOutOfRangeException);
        }
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    void lotto_range_test() {
        try {
            Lotto.of(1, 2, 3, 4, 5, 46);
        } catch (Exception e) {
            assertTrue(e instanceof LottoOutOfRangeException);
        }
    }

    @Test
    @DisplayName("로또 번호 중복 테스트")
    void lotto_duplicate_test() {
        try {
            Lotto.of(1, 2, 3, 4, 5, 5);
        } catch (Exception e) {
            assertTrue(e instanceof LottoDuplicatedNumberException);
        }
    }
}
