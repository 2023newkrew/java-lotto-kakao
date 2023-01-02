package lotto;

import lotto.model.Issuer;
import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssuerTest {

    @Test
    @DisplayName("로또 길이 테스트")
    void lotto_length_test() {
        Lotto lotto = Issuer.issue();
        assertEquals(lotto.length(), LottoSettings.MAX_LENGTH.getValue());
    }

    @Test
    @DisplayName("로또 숫자 범위 테스트")
    void lotto_range_test() {
        Lotto lotto = Issuer.issue();
        for (int index = 0; index < lotto.length(); index++) {
            assertTrue(lotto.getIthNumber(index) <= LottoSettings.MAX_RANGE.getValue() &&
                    lotto.getIthNumber(index) >= LottoSettings.MIN_RANGE.getValue());
        }
    }

    @Test
    @DisplayName("로또 숫자 중복 테스트")
    void lotto_duplicate_test() {
        Lotto lotto = Issuer.issue();
        assertEquals(new HashSet<>(lotto.getNumbers()).size(), lotto.length());
    }
}
