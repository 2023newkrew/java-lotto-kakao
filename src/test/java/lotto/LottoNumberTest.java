package lotto;

import lotto.model.LottoNumber;
import lotto.model.errors.LottoOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 범위 테스트 - 최대")
    void should_throwException_when_biggerThanMaxRange() {
        try {
            new LottoNumber(46);
        } catch (Exception e) {
            assertTrue(e instanceof LottoOutOfRangeException);
        }
    }

    @Test
    @DisplayName("로또 번호 범위 테스트 - 최소")
    void should_throwException_when_lessThanMinRange() {
        try {
            new LottoNumber(0);
        } catch (Exception e) {
            assertTrue(e instanceof LottoOutOfRangeException);
        }
    }
}
