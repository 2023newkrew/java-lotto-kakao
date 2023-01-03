package buyer;

import lotto.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyerResultTest {
    @DisplayName("BuyerResult 증가 함수 테스트")
    @Test
    void buyerAddTest() {
        BuyerResult buyerResult = new BuyerResult();
        buyerResult.matches(Rank.FIFTH);

        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));

        assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }
}
