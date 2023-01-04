package lotto;

import lotto.model.Issuer;
import lotto.model.Lotto;
import lotto.model.LottoList;
import lotto.model.LottoSettings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssuerTest {

    @Test
    @DisplayName("로또 길이 테스트")
    void lotto_length_test() {
        LottoList lottoList = Issuer.issue(1);
        Lotto lotto = lottoList.get(0);
        assertEquals(lotto.length(), LottoSettings.MAX_LENGTH.getValue());
    }

    @Test
    @DisplayName("로또 숫자 범위 테스트")
    void lotto_range_test() {
        LottoList lottoList = Issuer.issue(1);
        Lotto lotto = lottoList.get(0);
        for (int index = 0; index < lotto.length(); index++) {
            assertTrue(lotto.get(index) <= LottoSettings.MAX_RANGE.getValue() &&
                    lotto.get(index) >= LottoSettings.MIN_RANGE.getValue());
        }
    }

    @Test
    @DisplayName("로또 숫자 중복 테스트")
    void lotto_duplicate_test() {
        LottoList lottoList = Issuer.issue(1);
        Lotto lotto = lottoList.get(0);
        assertEquals(new HashSet<>(lotto.getNumbers()).size(), lotto.length());
    }

    @Test
    @DisplayName("발권 매수 테스트")
    void lotto_list_size_test() {
        LottoList lottoList = Issuer.issue(14);
        assertEquals(lottoList.length(), 14);
    }
}
