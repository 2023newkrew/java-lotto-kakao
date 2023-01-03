package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class PrizeCountMapTest {
    @Test
    void 로또의_당첨_정보를_넣으면_총_당첨_금액을_반환한다() {
        PrizeCountMap prizeCountMap = new PrizeCountMap(
                new HashMap<>() {{
                    put(LottoPrize.FIRST_PRIZE, 1);
                    put(LottoPrize.SECOND_PRIZE, 2);
                    put(LottoPrize.THIRD_PRIZE, 3);
                    put(LottoPrize.FOURTH_PRIZE, 4);
                    put(LottoPrize.FIFTH_PRIZE, 5);
                }}
        );

        final long expectedPrizeMoney = LottoPrize.FIRST_PRIZE.getPrizeMoney() * 1L +
                LottoPrize.SECOND_PRIZE.getPrizeMoney() * 2L +
                LottoPrize.THIRD_PRIZE.getPrizeMoney() * 3L +
                LottoPrize.FOURTH_PRIZE.getPrizeMoney() * 4L +
                LottoPrize.FIFTH_PRIZE.getPrizeMoney() * 5L;

        assertThat(prizeCountMap.getTotalPrizeMoney()).isEqualTo(expectedPrizeMoney);
    }
}
