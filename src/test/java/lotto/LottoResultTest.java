package lotto;

import lotto.domain.*;
import lotto.util.LottoPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class LottoResultTest {
    @ParameterizedTest
    @MethodSource("lottoMatchTestGenerator")
    @DisplayName("1등, 2등, 5등 당첨 테스트")
    void lottoMatchTest(Lotto myLotto, LottoRank expectedRank) {
        Lotto winningLotto = new Lotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(7);
        LottoResult result = new LottoResult(myLotto, winningLotto, bonusNumber);
        assertThat(result.getRank()).isEqualTo(expectedRank);
    }

    private static Stream<Arguments> lottoMatchTestGenerator() {
        return Stream.of(
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
                Arguments.of(new Lotto(1, 2, 3, 4, 5, 7), LottoRank.SECOND),
                Arguments.of(new Lotto(1, 2, 3, 8, 9, 10), LottoRank.FIFTH)
        );
    }

    @Test
    @DisplayName("로또 등수별 당첨 통계 테스트")
    void lottoStatisticsTest() {
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
    @DisplayName("상금, 수익률 일치 테스트")
    void lottoPrizeTest() {
        Statistics stat = new Statistics();
        stat.add(LottoRank.FIRST);
        stat.add(LottoRank.SECOND);
        stat.add(LottoRank.FIFTH);
        int totalPrize = (LottoRank.FIRST.PRIZE + LottoRank.SECOND.PRIZE + LottoRank.FIFTH.PRIZE);
        double profitRate = totalPrize / (double) (3 * LottoPayment.LOTTO_COST);
        assertThat(stat.getPrizeAmount()).isEqualTo(totalPrize);
        assertThat(stat.getProfitRate()).isCloseTo(profitRate, within(0.0001));
    }
}
