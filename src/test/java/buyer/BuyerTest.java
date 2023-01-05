package buyer;

import lotto.Lotto;
import lotto.Rank;
import lotto.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;


class BuyerTest {
    @DisplayName("로또 하나에 대한 결과 객체 확인")
    @Test
    void lotteryResult() {
        //given
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", 7);
        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));
        Buyer buyer = new Buyer(1000);
        //when
        buyer.buyLottery(1000, new Lotto("1, 2, 3, 12, 13, 14"));
        BuyerResult buyerResult = buyer.getResult(winningLotto);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("로또 뭉치에 대한 결과 객체 확인")
    @Test
    void lotteryListResult() {
        //given
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", 7);
        EnumMap<Rank, Integer> cpMap =
                new EnumMap<>(Map.ofEntries(Map.entry(Rank.FIFTH, 1), Map.entry(Rank.FOURTH, 1)));
        Buyer buyer = new Buyer(2000);
        //when
        buyer.buyLottery(1000, new Lotto("1, 2, 3, 12, 13, 14"));
        buyer.buyLottery(1000, new Lotto("1, 2, 3, 4, 12, 14"));
        BuyerResult buyerResult = buyer.getResult(winningLotto);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }
}