package javalotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LottoCountTest {

    @ParameterizedTest
    @MethodSource
    public void should_calculateCorrectCount_when_purchaseAmountAndUnitPriceGiven(PurchaseAmount purchaseAmount, int expectedResult) {
        LottoCount lottoCount = LottoCount.of(purchaseAmount);
        Assertions.assertThat(lottoCount).isEqualTo(LottoCount.withCount(expectedResult));
    }

    static Stream<Arguments> should_calculateCorrectCount_when_purchaseAmountAndUnitPriceGiven() {
        return Stream.of(
                Arguments.arguments(PurchaseAmount.from(2000), 2),
                Arguments.arguments(PurchaseAmount.from(12000), 12)
        );
    }

}