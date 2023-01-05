package buyer;

import lotto.Lotto;
import lotto.LottoGenerator;
import lotto.Rank;
import lotto.WinningLotto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        buyer.buyLottery(new Lotto("1, 2, 3, 12, 13, 14"));
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
        buyer.buyLottery(new Lotto("1, 2, 3, 12, 13, 14"));
        buyer.buyLottery(new Lotto("1, 2, 3, 4, 12, 14"));
        BuyerResult buyerResult = buyer.getResult(winningLotto);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("3000원 있는 buyer가 로또 3개까지 구매할 수 있음")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void canBuyLottosOfQuantityTest(int num) {
        //given
        Buyer buyer = new Buyer(3000);
        //when
        //then
        Assertions.assertThat(buyer.canBuyLottosOfQuantity(num)).isTrue();
    }

    @DisplayName("3000원 있는 buyer가 로또 4개 이상 구매할 수 없음")
    @ParameterizedTest
    @ValueSource(ints = {4,5,6})
    void cannotBuyLottosOfQuantityTest(int num) {
        //given
        Buyer buyer = new Buyer(3000);
        //when
        //then
        Assertions.assertThat(buyer.canBuyLottosOfQuantity(num)).isFalse();
    }
}