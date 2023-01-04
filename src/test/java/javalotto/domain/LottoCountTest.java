package javalotto.domain;

import javalotto.exception.lottocount.LottoCountNegativeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LottoCountTest {

    @ParameterizedTest
    @MethodSource
    public void should_calculateCorrectCount_when_purchaseAmountAndUnitPriceGiven(PurchaseAmount purchaseAmount, int unitPrice, int expectedResult) {
        LottoCount lottoCount = LottoCount.of(purchaseAmount, unitPrice);
        Assertions.assertThat(lottoCount.getCount()).isEqualTo(expectedResult);
    }

    static Stream<Arguments> should_calculateCorrectCount_when_purchaseAmountAndUnitPriceGiven() {
        return Stream.of(
                Arguments.arguments(PurchaseAmount.from(2000), 1000, 2),
                Arguments.arguments(PurchaseAmount.from(12000), 2000, 6)
        );
    }

    @Test
    public void should_throwException_when_getRemainExceptForResultIsNegative() {
        LottoCount lottoCount1 = LottoCount.withCount(10);
        LottoCount lottoCount2 = LottoCount.withCount(20);
        Assertions.assertThatThrownBy(() -> lottoCount1.getRemainExceptFor(lottoCount2))
                .isInstanceOf(LottoCountNegativeException.class);
    }
}