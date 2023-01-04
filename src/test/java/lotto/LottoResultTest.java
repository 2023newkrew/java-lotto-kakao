package lotto;

import lotto.model.MatchedResult;
import lotto.model.enums.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoResultTest {

    @Test
    @DisplayName("당첨 결과 동일성 테스트")
    void lotto_result_match_test() {
        assertEquals(LottoResult.match(new MatchedResult(3, true)), LottoResult.THREE_NUMBERS_MATCHED);
        assertEquals(LottoResult.match(new MatchedResult(1, true)), LottoResult.LOSE);
    }

    @Test
    @DisplayName("당첨 결과에 따른 상금 확인 테스트")
    void lotto_result_prize_money_test() {
        assertEquals(LottoResult.match(new MatchedResult(3, true)).getPrizeMoney(), 5000);
    }
}

