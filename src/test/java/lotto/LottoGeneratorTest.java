package lotto;

import buyer.Buyer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(buyer.getLotteries()).hasSize(2);
    }
}
