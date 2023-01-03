package lotto;

import buyer.BuyerResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LotteryResultTest {
    @DisplayName("당첨 번호 입력 테스트")
    @Test
    void matchTest() {
        //given
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        //when
        //then
        Assertions.assertThat(lotteryResult).isEqualTo(new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7));
    }

    @DisplayName("중복된 당첨 번호 입력 테스트")
    @Test
    void duplicateTest() {
        //given
        //when
        //then
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 6));
    }

    @DisplayName("로또 하나 받아서 통계 확인")
    @Test
    void singleStatistics() {
        //given
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Lottery lottery = new Lottery(List.of(1, 2, 3, 9, 10, 11));
        //when
        //then
        Assertions.assertThat(lotteryResult.getRank(lottery)).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("로또 하나에 대한 결과 객체 확인")
    @Test
    void lotteryResult() {
        //given
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));
        Lotteries lotteries = new Lotteries();
        //when
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 12, 13, 14)));
        BuyerResult buyerResult = lotteryResult.getResult(lotteries);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }

    @DisplayName("로또 뭉치에 대한 결과 객체 확인")
    @Test
    void lotteryListResult() {
        //given
        LotteryResult lotteryResult = new LotteryResult(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotteries lotteries = new Lotteries();
        EnumMap<Rank, Integer> cpMap =
                new EnumMap<>(Map.ofEntries(Map.entry(Rank.FIFTH, 1), Map.entry(Rank.FOURTH, 1)));
        //when
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 12, 13, 14)));
        lotteries.addLottery(new Lottery(List.of(1, 2, 3, 4, 12, 14)));
        BuyerResult buyerResult = lotteryResult.getResult(lotteries);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }
}
