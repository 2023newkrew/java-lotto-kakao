package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizesTest {

    @DisplayName("개별 상금을 합산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "FIRST, 2_000_000_000", "SECOND, 30_000_000", "THIRD, 150_000",
            "FOURTH, 50_000", "FIFTH, 5_000", "NOTHING, 0"
    })
    void sumFirstPrize(Prize prize, long expected) {
        Prizes prizes = new Prizes(
                Map.of(prize, 1L)
        );

        BigDecimal actual = prizes.getTotalAmount();

        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(expected));
    }

    @DisplayName("전체 상금을 합산한다.")
    @Test
    void sumFirstAndSecondPrize() {
        Prizes prizes = new Prizes(
                Map.of(Prize.FIRST, 1L,
                        Prize.SECOND, 2L,
                        Prize.THIRD, 3L,
                        Prize.FOURTH, 4L,
                        Prize.FIFTH, 5L,
                        Prize.NOTHING, 10L));

        BigDecimal actual = prizes.getTotalAmount();

        assertThat(actual).isEqualByComparingTo(BigDecimal.valueOf(2_060_675_000L));
    }
}