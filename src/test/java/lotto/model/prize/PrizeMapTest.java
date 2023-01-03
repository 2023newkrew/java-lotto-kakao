package lotto.model.prize;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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
    class getTotalProfit {

        @DisplayName("총 상금의 합산 반환")
        @ParameterizedTest
        @MethodSource
        void should_returnTotalProfit_when_givenPrizes(List<Prize> prizes, BigDecimal expected) {
            PrizeMap prizeMap = PrizeMap.from(prizes);

            Assertions.assertThat(prizeMap.getTotalProfit()).isEqualTo(expected);
        }

        List<Arguments> should_returnTotalProfit_when_givenPrizes() {
            return List.of(
                    Arguments.of(createPrizes(Prize.FIRST, 1), BigDecimal.valueOf(2_000_000_000L)),
                    Arguments.of(createPrizes(Prize.SECOND, 2), BigDecimal.valueOf(60_000_000L)),
                    Arguments.of(createPrizes(Prize.THIRD, 3), BigDecimal.valueOf(450_000L)),
                    Arguments.of(createPrizes(Prize.FOURTH, 4), BigDecimal.valueOf(200_000L)),
                    Arguments.of(createPrizes(Prize.FIFTH, 5), BigDecimal.valueOf(25_000L)),
                    Arguments.of(createPrizes(Prize.NOTHING, 6), BigDecimal.valueOf(0L))
            );
        }

        List<Prize> createPrizes(Prize prize, int count) {
            return IntStream.range(0, count)
                    .mapToObj(ignore -> prize)
                    .collect(Collectors.toList());
        }
    }
}