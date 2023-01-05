package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class MatchResultTest {
    @DisplayName("당첨 결과로부터 수익률을 계산한다.")
    @Test
    void calculateProfit() {
        Map<Ranking, Long> rankingCount = Map.of(
                Ranking.FIFTH, 1L,
                Ranking.OTHER, 3L
        );
        MatchResult matchResult = new MatchResult(rankingCount);
        assertThat(matchResult.calculateProfit()).isCloseTo(1.25, offset(0.01));
    }
}