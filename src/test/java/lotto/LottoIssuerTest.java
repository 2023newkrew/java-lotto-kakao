package lotto;

import lotto.model.Lotto;
import lotto.model.LottoIssuer;
import lotto.model.LottoList;
import lotto.model.errors.LottoOutOfRangeException;
import lotto.model.strategy.RandomAutomaticLottoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoIssuerTest {

    private LottoIssuer lottoIssuer;

    @BeforeEach
    void setUp() {
        lottoIssuer = new LottoIssuer(new RandomAutomaticLottoStrategy());
    }

    @Test
    @DisplayName("로또 길이 테스트")
    void lotto_length_test() {
        LottoList lottoList = lottoIssuer.issue(1);
        Lotto lotto = lottoList.get(0);
        assertEquals(lotto.length(), Lotto.NUMBER_LENGTH);
    }

    @Test
    @DisplayName("로또 번호 범위 테스트")
    void lotto_range_test() {
        try {
            LottoList lottoList = lottoIssuer.issue(1);
            lottoList.get(0);
        } catch (Exception e) {
            assertTrue(e instanceof LottoOutOfRangeException);
        }
    }

    @Test
    @DisplayName("로또 번호 중복 테스트")
    void lotto_duplicate_test() {
        LottoList lottoList = lottoIssuer.issue(1);
        Lotto lotto = lottoList.get(0);
        assertEquals(new HashSet<>(lotto.getNumbers()).size(), lotto.length());
    }

    @Test
    @DisplayName("발권 매수 테스트")
    void lotto_list_size_test() {
        LottoList lottoList = lottoIssuer.issue(14);
        assertEquals(lottoList.length(), 14);
    }
}
