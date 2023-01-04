package lotto;

import lotto.domain.LottoRank;
import lotto.domain.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsTest {
    @Test
    @DisplayName("여러개의 로또 당첨시 당첨 개수 제공")
    void 일등한개이등한개오등한개개수() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);

        assertThat(stat.getByRank(LottoRank.FIRST)).isEqualTo(1);
        assertThat(stat.getByRank(LottoRank.SECOND)).isEqualTo(1);
        assertThat(stat.getByRank(LottoRank.THIRD)).isEqualTo(0);
        assertThat(stat.getByRank(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(stat.getByRank(LottoRank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("1등:1, 2등:1, 5등:1의 당첨금액은 20억 3천만 5천원이다.")
    void 일등한개이등한개오등한개상금() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);

        // 1등 20억, 2등 3천만, 3등 150만, 4등 5만, 5등 5천
        assertThat(stat.getPrizeAmount()).isEqualTo(2_030_005_000);
    }

    @Test
    @DisplayName("1등:1, 2등:1, 5등:1의 수익률은 676668.3333333334이다.")
    void name() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);
        assertThat(stat.getProfitRate()).isEqualTo(676668.3333333334);
    }
}
