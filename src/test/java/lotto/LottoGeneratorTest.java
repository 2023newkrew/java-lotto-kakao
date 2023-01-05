package lotto;

import buyer.Buyer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("수동 로또 구매 후 동일한지 확인")
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
}
