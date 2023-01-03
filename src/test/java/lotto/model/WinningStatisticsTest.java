package lotto.model;

import lotto.model.prize.Prize;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static lotto.TestUtil.repeatPrizes;

class WinningStatisticsTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class getProfitRate {

        @DisplayName("단일 수익률 확인")
        @ParameterizedTest
        @MethodSource
        void should_returnProfitRate_when_givenPrize(Prize prize, BigDecimal expected) {
            Money money = Money.valueOf(1000L);
            PrizeMap prizeMap = PrizeMap.from(List.of(prize));

            WinningStatistics winningStatistics = WinningStatistics.from(money, money, prizeMap);

            Assertions.assertThat(winningStatistics.getProfitRate()).isEqualByComparingTo(expected);
        }

        List<Arguments> should_returnProfitRate_when_givenPrize() {
            return List.of(
                    Arguments.of(Prize.FIRST, BigDecimal.valueOf(2_000_000L)),
                    Arguments.of(Prize.SECOND, BigDecimal.valueOf(30_000L)),
                    Arguments.of(Prize.THIRD, BigDecimal.valueOf(150L)),
                    Arguments.of(Prize.FOURTH, BigDecimal.valueOf(50L)),
                    Arguments.of(Prize.FIFTH, BigDecimal.valueOf(5L)),
                    Arguments.of(Prize.NOTHING, BigDecimal.valueOf(0L))
            );
        }

        @DisplayName("종합 수익률 확인")
        @Test
        void asd() {
            List<Prize> prizes = new ArrayList<>();
            prizes.addAll(repeatPrizes(Prize.FIRST, 1));
            prizes.addAll(repeatPrizes(Prize.SECOND, 2));
            prizes.addAll(repeatPrizes(Prize.THIRD, 3));
            prizes.addAll(repeatPrizes(Prize.FOURTH, 4));
            prizes.addAll(repeatPrizes(Prize.FIFTH, 5));
            prizes.addAll(repeatPrizes(Prize.NOTHING, 10));

            WinningStatistics winningStatistics = WinningStatistics.from(Money.valueOf(25345L), Money.valueOf(25000L), PrizeMap.from(prizes));

            BigDecimal actual = winningStatistics.getProfitRate();
            BigDecimal expected = BigDecimal.valueOf(2_060_675_345L).divide(BigDecimal.valueOf(25345L), 2, RoundingMode.DOWN);

            Assertions.assertThat(actual).isEqualByComparingTo(expected);
        }
    }
}