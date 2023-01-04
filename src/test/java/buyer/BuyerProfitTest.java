package buyer;

import lotto.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class BuyerProfitTest {
    @DisplayName("수익률 테스트")
    @Test
    void profitTest() {
        //given
        List<Lotto> lotteries = new ArrayList<>();
        lotteries.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 8);
        //when
        BuyerResult buyerResult = winningLotto.getResult(lotteries);
        //then
        Assertions.assertThat(buyerResult.getProfit()).isEqualTo(new BuyerProfit((double) Rank.FIRST.prize / 1000));

    }
}
