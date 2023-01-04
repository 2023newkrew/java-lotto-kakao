package lotto;

import lotto.model.Issuer;
import lotto.model.Lotto;
import lotto.model.LottoList;
import lotto.model.LottoNumber;
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
        assertEquals(lotto.length(), Lotto.NUMBER_LENGTH);
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    void lotto_range_test() {
        LottoList lottoList = Issuer.issue(1);
        Lotto lotto = lottoList.get(0);
        for (int index = 0; index < lotto.length(); index++) {
            assertTrue(lotto.get(index) <= LottoNumber.MIN_RANGE &&
                    lotto.get(index) >= LottoNumber.MAX_RANGE);
        }
    }

    @Test
    @DisplayName("로또 번호 중복 테스트")
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
