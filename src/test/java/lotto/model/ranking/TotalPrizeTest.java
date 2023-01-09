package lotto.model.ranking;

import lotto.TestUtil;
import lotto.model.store.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

class TotalPrizeTest {
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("총 상금 계산")
        @ParameterizedTest
        @MethodSource
        void should_returnTotalPrize_when_givenRankings(List<LottoRanking> rankings, Money expected) {
            RankingCounts rankingCounts = RankingCounts.from(rankings);

            TotalPrize actual = TotalPrize.from(rankingCounts);

            Assertions.assertThat(actual.getMoney()).isEqualTo(expected);
        }

        List<Arguments> should_returnTotalPrize_when_givenRankings() {
            return List.of(
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.FIRST, 1), Money.valueOf(2_000_000_000L)),
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.SECOND, 2), Money.valueOf(60_000_000L)),
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.THIRD, 3), Money.valueOf(450_000L)),
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.FOURTH, 4), Money.valueOf(200_000L)),
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.FIFTH, 5), Money.valueOf(25_000L)),
                    Arguments.of(TestUtil.repeatRankings(LottoRanking.NOTHING, 6), Money.valueOf(0L)),
                    Arguments.of(List.of(), Money.valueOf(0L))
            );
        }
    }
}
