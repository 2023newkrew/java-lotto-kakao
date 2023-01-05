package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningStatisticsTest {

    @DisplayName("개별 상금의 수익율을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "FIRST, 2_000_000", "SECOND, 30_000", "THIRD, 150",
            "FOURTH, 50", "FIFTH, 5", "NOTHING, 0"
    })
    void calculateSinglePrizeProfitRate(Prize prize, long expectedProfit) {
        WinningStatistics winningStatistics = new WinningStatistics(
                new Money(1000), Map.of(prize, 1L)
        );

        BigDecimal profit = winningStatistics.getProfitRate();

        assertThat(profit).isEqualByComparingTo(BigDecimal.valueOf(expectedProfit));
    }

    @DisplayName("수익율전체 상금을 합산한다.")
    @Test
    void calculateMultiPrizeProfitRate() {
        WinningStatistics winningStatistics = new WinningStatistics(
                new Money(25345), Map.of(Prize.FIRST, 1L,
                        Prize.SECOND, 2L,
                        Prize.THIRD, 3L,
                        Prize.FOURTH, 4L,
                        Prize.FIFTH, 5L,
                        Prize.NOTHING, 10L));

        BigDecimal actual = winningStatistics.getProfitRate();
        BigDecimal expected = BigDecimal.valueOf(2_060_675_345L).divide(BigDecimal.valueOf(25345L), 2, RoundingMode.DOWN);

        assertThat(actual).isEqualByComparingTo(expected);
    }
}