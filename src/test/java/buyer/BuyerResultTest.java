package buyer;

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
    @DisplayName("로또 하나에 대한 결과 객체 확인")
    @Test
    void lotteryResult() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Buyer buyer = new Buyer(1000);
        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 12, 13, 14)));

        BuyerResult buyerResult = buyer.getBuyerResult(lotteryResult);
        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));

        assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("로또 뭉치에 대한 결과 객체 확인")
    @Test
    void lotteryListResult() {
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Buyer buyer = new Buyer(2000);
        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 12, 13, 14)));
        buyer.buyLottery(new Lottery(List.of(1, 2, 3, 4, 12, 14)));

        BuyerResult buyerResult = buyer.getBuyerResult(lotteryResult);

        EnumMap<Rank, Integer> cpMap =
                new EnumMap<>(Map.ofEntries(Map.entry(Rank.FIFTH, 1), Map.entry(Rank.FOURTH, 1)));

        assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("buyerResult의 결과의 총 상금 금액 확인")
    @Test
    void totalPrize() {
        EnumMap<Rank, Integer> map =
                new EnumMap<>(Map.ofEntries(Map.entry(Rank.FIFTH, 1), Map.entry(Rank.FOURTH, 1)));

        BuyerResult buyerResult = new BuyerResult(map);

        assertThat(buyerResult.getTotalPrize()).isEqualTo(Rank.FIFTH.prize + Rank.FOURTH.prize);
    }
}
