package lotto.model.campany;

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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LottoStatsTest {


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class from {

        @DisplayName("등수가 없을 경우 예외 발생")
        @ParameterizedTest
        @NullSource
        void should_returnTotalProfit_when_givenRankings(List<LottoRanking> rankings) {
            Assertions.assertThatThrownBy(()->LottoStats.from(rankings))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 등수가 없습니다.");
        }

        @DisplayName("생성하면서 총 상금 계산")
        @ParameterizedTest
        @MethodSource
        void should_returnTotalProfit_when_givenRankings(List<LottoRanking> rankings, Money expected) {
            LottoStats stats = LottoStats.from(rankings);

            Assertions.assertThat(stats.getTotalPrize()).isEqualTo(expected);
        }

        List<Arguments> should_returnTotalProfit_when_givenRankings() {
            return List.of(
                    Arguments.of(repeatRankings(LottoRanking.FIRST, 1), Money.valueOf(2_000_000_000L)),
                    Arguments.of(repeatRankings(LottoRanking.SECOND, 2), Money.valueOf(60_000_000L)),
                    Arguments.of(repeatRankings(LottoRanking.THIRD, 3), Money.valueOf(450_000L)),
                    Arguments.of(repeatRankings(LottoRanking.FOURTH, 4), Money.valueOf(200_000L)),
                    Arguments.of(repeatRankings(LottoRanking.FIFTH, 5), Money.valueOf(25_000L)),
                    Arguments.of(repeatRankings(LottoRanking.NOTHING, 6), Money.valueOf(0L)),
                    Arguments.of(List.of(), Money.valueOf(0L))
            );
        }
    }


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class countBy {

        @DisplayName("각 랭킹의 갯수 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnCount_when_givenRankings(List<LottoRanking> rankings, LottoRanking ranking, long expected) {
            LottoStats stats = LottoStats.from(rankings);

            Assertions.assertThat(stats.countBy(ranking)).isEqualTo(expected);
        }

        List<Arguments> should_returnCount_when_givenRankings() {
            List<LottoRanking> rankings = List.of(
                    LottoRanking.FIRST,
                    LottoRanking.SECOND, LottoRanking.SECOND,
                    LottoRanking.THIRD, LottoRanking.THIRD, LottoRanking.THIRD,
                    LottoRanking.FOURTH, LottoRanking.FOURTH, LottoRanking.FOURTH, LottoRanking.FOURTH,
                    LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH, LottoRanking.FIFTH
            );

            return List.of(
                    Arguments.of(rankings, LottoRanking.NOTHING, 0L),
                    Arguments.of(rankings, LottoRanking.FIRST, 1L),
                    Arguments.of(rankings, LottoRanking.SECOND, 2L),
                    Arguments.of(rankings, LottoRanking.THIRD, 3L),
                    Arguments.of(rankings, LottoRanking.FOURTH, 4L),
                    Arguments.of(rankings, LottoRanking.FIFTH, 5L)
            );
        }
    }

    public static List<LottoRanking> repeatRankings(LottoRanking ranking, int count) {
        return IntStream.range(0, count)
                .mapToObj(ignore -> ranking)
                .collect(Collectors.toList());
    }
}