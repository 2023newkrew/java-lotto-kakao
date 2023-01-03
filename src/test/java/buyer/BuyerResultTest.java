package buyer;

import lotto.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import java.util.Map;

public class BuyerResultTest {
    @DisplayName("BuyerResult 증가 함수 테스트")
    @Test
    void buyerAddTest() {
        //given
        BuyerResult buyerResult = new BuyerResult();
        EnumMap<Rank, Integer> cpMap = new EnumMap<>(Map.of(Rank.FIFTH, 1));
        //when
        buyerResult.matches(Rank.FIFTH);
        //then
        Assertions.assertThat(buyerResult).isEqualTo(new BuyerResult(cpMap));
    }
}
