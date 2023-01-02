package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BuyerTest {
    @DisplayName("받은 금액만큼의 로또를 사는지 확인")
    @Test
    void countLotto(){
        Buyer buyer = new Buyer(14000);

        assertThat(buyer.getLotteries().size()).isEqualTo(14);
    }
}
