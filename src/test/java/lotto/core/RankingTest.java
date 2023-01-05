package lotto.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RankingTest {
    @DisplayName("1등 로또 총 당첨금 계산")
    @Test
    void totalPrice1() {
        Map<Ranking, Integer> rankingCount = Map.of(
                Ranking.FIRST, 1,
                Ranking.SECOND, 0,
                Ranking.THIRD, 0,
                Ranking.FOURTH, 0,
                Ranking.FIFTH, 0,
                Ranking.OTHER, 0
        );
        assertThat(Ranking.totalPrice(rankingCount)).isEqualTo(2000000000L);
    }

    @DisplayName("로또 중복 당첨금 계산")
    @Test
    void totalPrice_multiple() {
        Map<Ranking, Integer> rankingCount = Map.of(
                Ranking.FIRST, 0,
                Ranking.SECOND, 0,
                Ranking.THIRD, 1,
                Ranking.FOURTH, 2,
                Ranking.FIFTH, 5,
                Ranking.OTHER, 10
        );
        assertThat(Ranking.totalPrice(rankingCount)).isEqualTo(1500000 + 50000 * 2 + 5000 * 5);
    }
}