package buyer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerResultTest {
    @DisplayName("BuyerResult 증가 함수 테스트")
    @Test
    void buyerAddTest(){
        BuyerResult buyerResult = new BuyerResult(new ArrayList<>(List.of(0,0,0,0,0)));

        buyerResult.matches(BuyerResult.Result.FIFTH);

        assertThat(buyerResult).isEqualTo(new BuyerResult(List.of(1,0,0,0,0)));
    }
}
