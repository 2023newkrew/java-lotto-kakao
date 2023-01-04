package buyer;

import lotto.Lotteries;
import lotto.Lottery;
import lotto.LotteryResult;
import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerResultTest {
    @DisplayName("Lotteries와 LotteryResult를 받아 LotteryResult와 매칭되는 Lotteries의 Lottery 개수를 가진 BuyerResult를 생성한다")
    @Test
    void lotteryResult() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotteries lotteries = new Lotteries();
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 12, 13, 14)));
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 4, 13, 14)));

        BuyerResult buyerResult = new BuyerResult(lotteries, lotteryResult);

        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1, Rank.FOURTH, 1));

        assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("buyerResult의 당첨된 총 상금을 계산한다")
    @Test
    void totalPrize() {
        EnumMap<Rank, Integer> map =
                new EnumMap<>(Map.ofEntries(Map.entry(Rank.FIFTH, 1), Map.entry(Rank.FOURTH, 1)));

        BuyerResult buyerResult = new BuyerResult(map);

        assertThat(buyerResult.getTotalPrize()).isEqualTo(Rank.FIFTH.prize + Rank.FOURTH.prize);
    }
}
