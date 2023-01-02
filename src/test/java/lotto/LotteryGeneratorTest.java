package lotto;

import buyer.Buyer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryGeneratorTest {
    @DisplayName("Buyer를 받아 살 수 있는 로또 반환")
    @Test
    void lotteryGenerate(){
        LotteryGenerator lotteryGenerator = new LotteryGenerator();
        Buyer buyer = new Buyer(2000);

        lotteryGenerator.generate(buyer);

        assertThat(buyer.getLotteries()).hasSize(2);
    }
}
