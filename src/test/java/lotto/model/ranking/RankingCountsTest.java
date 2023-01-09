package lotto.model.ranking;

import lotto.model.store.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

class RankingCountsTest {


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("등수가 없을 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        void should_returnTotalProfit_when_givenRankings(List<LottoRanking> rankings) {
            Assertions.assertThatThrownBy(() -> RankingCounts.from(rankings))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 등수가 없습니다.");
        }
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class countBy {

        @DisplayName("각 랭킹의 갯수가 정확한지 확인")
        @ParameterizedTest
        @MethodSource
        void should_returnRankingCount_when_givenRanking(LottoRanking ranking, long rankingCount) {
            List<LottoRanking> rankings = createTestRankings();
            RankingCounts rankingCounts = RankingCounts.from(rankings);

            Assertions.assertThat(rankingCounts.countBy(ranking)).isEqualTo(rankingCount);
        }

        List<Arguments> should_returnRankingCount_when_givenRanking() {

            return List.of(
                    Arguments.of(LottoRanking.NOTHING, 0L),
                    Arguments.of(LottoRanking.FIRST, 1L),
                    Arguments.of(LottoRanking.SECOND, 2L),
                    Arguments.of(LottoRanking.THIRD, 3L),
                    Arguments.of(LottoRanking.FOURTH, 4L),
                    Arguments.of(LottoRanking.FIFTH, 5L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class sumEachPrize {


        @DisplayName("등수 별 총 상금 계산")
        @ParameterizedTest
        @MethodSource
        void should_returnTotalProfit_when_givenRankings(LottoRanking ranking, Money expected) {
            List<LottoRanking> rankings = createTestRankings();
            RankingCounts rankingCounts = RankingCounts.from(rankings);

            Assertions.assertThat(rankingCounts.sumEachPrize(ranking)).isEqualTo(expected);
        }

        List<Arguments> should_returnTotalProfit_when_givenRankings() {
            return List.of(
                    Arguments.of(LottoRanking.FIRST, Money.valueOf(2_000_000_000L)),
                    Arguments.of(LottoRanking.SECOND, Money.valueOf(60_000_000L)),
                    Arguments.of(LottoRanking.THIRD, Money.valueOf(450_000L)),
                    Arguments.of(LottoRanking.FOURTH, Money.valueOf(200_000L)),
                    Arguments.of(LottoRanking.FIFTH, Money.valueOf(25_000L)),
                    Arguments.of(LottoRanking.NOTHING, Money.valueOf(0L))
            );
        }
    }

    private static List<LottoRanking> createTestRankings() {
        return List.of(
                LottoRanking.FIRST,
                LottoRanking.SECOND, LottoRanking.SECOND,
                LottoRanking.THIRD, LottoRanking.THIRD, LottoRanking.THIRD,
                LottoRanking.FOURTH, LottoRanking.FOURTH, LottoRanking.FOURTH, LottoRanking.FOURTH,
                LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH
        );
    }
}