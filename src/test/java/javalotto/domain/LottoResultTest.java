package javalotto.domain;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @Test
    void should_returnCorrectString_when_givenWinningLottoAndLottos() {
        WinningLotto winningLotto = WinningLotto.of(Lotto.from(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lottos lottos = Lottos.from(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 45))
        ));
        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        assertThat(lottoResult.toString())
                .isEqualTo("3개 일치 (5000원)- 0개\n" +
                        "4개 일치 (50000원)- 0개\n" +
                        "5개 일치 (1500000원)- 1개\n" +
                        "5개 일치, 보너스 볼 일치(30000000원)- 1개\n" +
                        "6개 일치 (2000000000원)- 2개");
    }

    @ParameterizedTest
    @MethodSource
    void should_getTotalRateOfReturn_when_givenPurchaseAmount(LottoResult lottoResult, PurchaseAmount purchaseAmount, double expectedRateOfReturn) {
        Double totalRateOfReturn = lottoResult.getTotalRateOfReturn(purchaseAmount);
        assertThat(totalRateOfReturn).isCloseTo(expectedRateOfReturn, Percentage.withPercentage(0.01));
    }

    static Stream<Arguments> should_getTotalRateOfReturn_when_givenPurchaseAmount() {
        LottoResult lottoResultWithBigPrize = LottoResult.of(Map.of(
                Rank.FIRST, 2,
                Rank.SECOND, 3,
                Rank.THIRD, 4,
                Rank.FOURTH, 5,
                Rank.FIFTH, 6
        )); // total sum of prize: 4_096_280_000
        LottoResult lottoResultWithSmallPrize = LottoResult.of(Map.of(
                Rank.FIRST, 0,
                Rank.SECOND, 0,
                Rank.THIRD, 0,
                Rank.FOURTH, 0,
                Rank.FIFTH, 1
        )); // total sum of prize: 5000
        return Stream.of(
                Arguments.of(lottoResultWithBigPrize, PurchaseAmount.from(1000), 4_096_280.0),
                Arguments.of(lottoResultWithBigPrize, PurchaseAmount.from(10000), 4_096_28.0),
                Arguments.of(lottoResultWithSmallPrize, PurchaseAmount.from(17000), 0.29411),
                Arguments.of(lottoResultWithSmallPrize, PurchaseAmount.from(41000), 0.12195)
        );
    }
}