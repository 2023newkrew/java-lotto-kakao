package lotto.models;

import static lotto.common.LottoConfiguration.LOTTO_COUNT;
import static lotto.common.LottoConfiguration.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {
    @Test
    @DisplayName("로또 리스트와 지난 주 정답으로부터 로또 통계를 도출할 수 있다.")
    public void testCreateLottoStatistics() {
        int budget = LOTTO_PRICE * 10;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            lottos.add(new Lotto(IntStream
                    .range(i, i + LOTTO_COUNT)
                    .boxed()
                    .collect(Collectors.toList())));
        }
        Goal goal = new Goal(IntStream
                .rangeClosed(2, LOTTO_COUNT + 1)
                .boxed()
                .collect(Collectors.toList()),
                1);
        LottoStatistics statistics = new LottoStatistics(goal, lottos, budget);
        for (LottoResult rank : LottoResult.values()) {
            if (rank == LottoResult.NONE) {
                assertThat(statistics.getCountOf(rank)).isEqualTo(5);
                continue;
            }
            assertThat(statistics.getCountOf(rank)).isEqualTo(1);
        }
        long totalPrize = Arrays.stream(LottoResult.values()).mapToLong(LottoResult::getPrize).reduce(Long::sum).orElse(0);
        assertThat(statistics.getEarningsRate()).isEqualTo((double) totalPrize / budget);
    }
}