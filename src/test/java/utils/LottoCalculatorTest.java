package utils;

import domain.Payment;
import domain.Rank;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCalculatorTest {

    @Test
    void 당첨_현황에_맞게_수익률을_계산한다() {
        // given
        Payment payment = new Payment(14000);
        Map<Rank, Integer> rankMap = Map.of(
                Rank.FIRST_PLACE, 0,
                Rank.SECOND_PLACE, 0,
                Rank.THIRD_PLACE, 0,
                Rank.FOURTH_PLACE, 0,
                Rank.FIFTH_PLACE, 1
        );

        // when, then
        assertThat(LottoCalculator.calculateYield(payment, rankMap)).isEqualTo(0.35);

    }

}
