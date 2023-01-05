package domain.buyer;

import domain.lotto.Lotteries;
import domain.lotto.Lottery;
import domain.lotto.LotteryResult;
import domain.lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertThat(buyerResult.getTotalPrize()).isEqualTo(Rank.FIFTH.prize + Rank.FOURTH.prize);
    }
}
