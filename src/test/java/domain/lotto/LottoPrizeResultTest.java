package domain.lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import service.LottoShop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class LottoPrizeResultTest {

    Map<LottoPrize, Integer> prizeCounts;

    @BeforeEach
    void setUp() {
        prizeCounts = new HashMap<>();
        Arrays.stream(LottoPrize.values())
                        .forEach(lottoPrize -> prizeCounts.put(lottoPrize, 0));
    }

    @DisplayName("Map<LottoPrize, Integer>를 주입받아 LottoResult를 생성한다")
    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> new LottoPrizeResult(prizeCounts));
    }

    @DisplayName("잘못된 등수 카운트가 들어오면 예외가 발생한다")
    @Test
    void create_throw() {
        prizeCounts.remove(LottoPrize.FIRST_PRIZE);
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoPrizeResult(prizeCounts));
    }

    @DisplayName("로또 구매 횟수로 수익률을 계산한다")
    @ParameterizedTest
    @ValueSource(ints = {14, 1000, 1})
    void calculateEarningRate(int lottoPurchaseCount) {
        prizeCounts.put(LottoPrize.FIRST_PRIZE, 1);
        LottoPrizeResult lottoPrizeResult = new LottoPrizeResult(prizeCounts);

        LottoEarningRate expected = new LottoEarningRate(
                (double) LottoPrize.FIRST_PRIZE.getPrizeMoney() / (lottoPurchaseCount * LottoShop.LOTTO_PRICE));

        LottoEarningRate result = lottoPrizeResult.calculateEarningRate(lottoPurchaseCount);

        int floor = 1_000_000;
        assertThat(Math.floor(result.getEarningRate() * floor))
                .isEqualTo(Math.floor(expected.getEarningRate() * floor));
    }

    @DisplayName("로또 구매 횟수가 0 이하이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -100})
    void calculateEarningRate_throw(int lottoPurchaseCount) {
        LottoPrizeResult lottoPrizeResult = new LottoPrizeResult(prizeCounts);
        assertThatIllegalArgumentException().isThrownBy(() -> lottoPrizeResult.calculateEarningRate(lottoPurchaseCount));
    }
}
