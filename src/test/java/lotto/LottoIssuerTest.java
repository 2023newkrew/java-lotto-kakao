package lotto;

import lotto.model.LottoIssuer;
import lotto.model.LottoList;
import lotto.model.strategy.RandomAutomaticLottoStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoIssuerTest {

    private LottoIssuer lottoIssuer;

    @BeforeEach
    void setUp() {
        lottoIssuer = new LottoIssuer(new RandomAutomaticLottoStrategy());
    }

    @Test
    @DisplayName("발권 매수 테스트")
    void lotto_list_size_test() {
        LottoList lottoList = lottoIssuer.issue(14);
        assertEquals(lottoList.length(), 14);
    }
}
