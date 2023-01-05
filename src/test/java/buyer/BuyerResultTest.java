package buyer;

import lotto.Lotto;
import lotto.Rank;
import lotto.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class BuyerResultTest {
    @DisplayName("BuyerResult 증가 함수 테스트")
    @Test
    void buyerAddTest() {
        //given
        BuyerResult buyerResult = new BuyerResult();
        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));
        //when
        buyerResult.matches(Rank.FIFTH);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("수익률 테스트")
    @Test
    void profitTest() {
        //given
        Buyer buyer = new Buyer(1000);
        buyer.buyLottery(1000, new Lotto("1, 2, 3, 4, 5, 6"));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 8);
        //when
        BuyerResult buyerResult = buyer.getResult(winningLotto);
        //then
        Assertions.assertThat(buyerResult.getProfit()).isEqualTo((double) Rank.FIRST.prize / 1000);

    }
}
