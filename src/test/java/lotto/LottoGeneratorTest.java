package lotto;

import buyer.Buyer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoGeneratorTest {
    @DisplayName("Buyer를 받아 살 수 있는 로또 반환")
    @Test
    void lotteryGenerate() {
        //given
        LottoGenerator lottoGenerator = new LottoGenerator();
        Buyer buyer = new Buyer(2000);
        //when
        lottoGenerator.autoGenerateRemaining(buyer);
        //then
        Assertions.assertThat(buyer.getLottos()).hasSize(2);
    }

    @DisplayName("수동 로또 하나 구매 후 동일한지 확인")
    @Test
    void manualGenerationTest() {
        //given
        LottoGenerator lottoGenerator = new LottoGenerator();
        Buyer buyer = new Buyer(1000);
        //when
        lottoGenerator.manuallyGenerateOne(buyer, "1,2,3,4,5,6");
        //then
        Assertions.assertThat(buyer.getLottos()).containsExactly(new Lotto("1,2,3,4,5,6"));
    }

    @DisplayName("수동 로또 2개 구매 후 동일한지 확인")
    @Test
    void manuallyGenerateTest() {
        //given
        LottoGenerator lottoGenerator = new LottoGenerator();
        Buyer buyer = new Buyer(2000);
        List<String> stringList = new ArrayList<>(List.of("1,2,3,4,5,6", "10,11,12,13,14,15"));
        Lotto lotto1 = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("10,11,12,13,14,15");
        //when
        lottoGenerator.manuallyGenerate(buyer, stringList);
        //then
        Assertions.assertThat(buyer.getLottos()).containsExactlyInAnyOrder(lotto1, lotto2);
    }
}
