package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningConditionTest {
    @Test
    void 결과_매치시_상금반환() {
        Assertions.assertThat(WinningCondition.FIRST_PRIZE
                        .getPrizeIfMatch(new LottoResult(6, false)))
                .isEqualTo(new Prize(LottoConstants.FIRST_PRIZE_CASH));
    }

    @Test
    void 결과_매치안될시_상금_0원_반환() {
        Assertions.assertThat(WinningCondition.FIRST_PRIZE
                        .getPrizeIfMatch(new LottoResult(0, false)))
                .isEqualTo(new Prize(0L));
    }
}
