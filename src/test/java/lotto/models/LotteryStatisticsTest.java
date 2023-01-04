package lotto.models;

import static lotto.common.LotteryConfiguration.LOTTERY_COUNT;
import static lotto.common.LotteryConfiguration.LOTTERY_PRICE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.models.enums.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryStatisticsTest {
    @Test
    @DisplayName("로또 리스트와 지난 주 정답으로부터 로또 통계를 도출할 수 있다.")
    public void testCreateLotteriestatistics() {
        int budget = LOTTERY_PRICE * 10;
        List<Lottery> lotteries = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            lotteries.add(new Lottery(IntStream
                    .range(i, i + LOTTERY_COUNT)
                    .boxed()
                    .collect(Collectors.toList())));
        }
        WinningLottery winningLottery = new WinningLottery(IntStream
                .rangeClosed(2, LOTTERY_COUNT + 1)
                .boxed()
                .collect(Collectors.toList()),
                1);
        LotteryStatistics statistics = new LotteryStatistics(winningLottery, lotteries, budget);
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                assertThat(statistics.getCountOf(rank)).isEqualTo(5);
                continue;
            }
            assertThat(statistics.getCountOf(rank)).isEqualTo(1);
        }
        long totalPrize = Arrays.stream(Rank.values()).mapToLong(Rank::getPrize).reduce(Long::sum).orElse(0);
        assertThat(statistics.getEarningsRate()).isEqualTo((double) totalPrize / budget);
    }
}