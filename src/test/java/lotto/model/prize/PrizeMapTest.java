package lotto.model.prize;

import lotto.model.vo.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PrizeMapTest {


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class countBy {

        @DisplayName("Prize의 갯수 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnCount_when_givenPrizes(List<Prize> prizes, Prize prize, long expected) {
            PrizeMap prizeMap = PrizeMap.from(prizes);

            Assertions.assertThat(prizeMap.countBy(prize)).isEqualTo(expected);
        }

        List<Arguments> should_returnCount_when_givenPrizes() {
            List<Prize> prizes = List.of(
                    Prize.FIRST,
                    Prize.SECOND, Prize.SECOND,
                    Prize.THIRD, Prize.THIRD, Prize.THIRD,
                    Prize.FOURTH, Prize.FOURTH, Prize.FOURTH, Prize.FOURTH,
                    Prize.FIFTH, Prize.FIFTH, Prize.FIFTH, Prize.FIFTH, Prize.FIFTH
            );

            return List.of(
                    Arguments.of(prizes, Prize.NOTHING, 0L),
                    Arguments.of(prizes, Prize.FIRST, 1L),
                    Arguments.of(prizes, Prize.SECOND, 2L),
                    Arguments.of(prizes, Prize.THIRD, 3L),
                    Arguments.of(prizes, Prize.FOURTH, 4L),
                    Arguments.of(prizes, Prize.FIFTH, 5L)
            );
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getProfitRate {

        @DisplayName("총 수익률 계산")
        @ParameterizedTest
        @MethodSource
        void should_returnProfitRate_when_givenPrizes(Prize prize, BigDecimal expected) {
            Money money = Money.valueOf(1000L);

            PrizeMap prizeMap = PrizeMap.from(List.of(prize));

            Assertions.assertThat(prizeMap.getProfitRate(money, money)).isEqualByComparingTo(expected);
        }

        List<Arguments> should_returnProfitRate_when_givenPrizes() {
            return List.of(
                    Arguments.of(Prize.FIRST, BigDecimal.valueOf(2_000_000L)),
                    Arguments.of(Prize.SECOND, BigDecimal.valueOf(30_000L)),
                    Arguments.of(Prize.THIRD, BigDecimal.valueOf(150L)),
                    Arguments.of(Prize.FOURTH, BigDecimal.valueOf(50L)),
                    Arguments.of(Prize.FIFTH, BigDecimal.valueOf(5L)),
                    Arguments.of(Prize.NOTHING, BigDecimal.valueOf(0L))
            );
        }

        List<Prize> createPrizes(Prize prize, int count) {
            return IntStream.range(0, count)
                    .mapToObj(ignore -> prize)
                    .collect(Collectors.toList());
        }
    }
}