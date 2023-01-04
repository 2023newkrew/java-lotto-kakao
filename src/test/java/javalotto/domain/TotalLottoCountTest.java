package javalotto.domain;

import javalotto.exception.lotto.InvalidLottoCountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TotalLottoCountTest {
    @ParameterizedTest
    @MethodSource
    void should_calculateCorrectAutoCount_when_manualCountAndPurchaseAmountGiven(LottoCount manualLottoCount, PurchaseAmount purchaseAmount, int expectedResult) {
        TotalLottoCount lottoCount = TotalLottoCount.of(manualLottoCount, purchaseAmount);
        assertThat(lottoCount.getAutoLottoCount()).isEqualTo(expectedResult);
    }

    static Stream<Arguments> should_calculateCorrectAutoCount_when_manualCountAndPurchaseAmountGiven() {
        return Stream.of(
                Arguments.arguments(LottoCount.from(1), PurchaseAmount.from(2000), 1),
                Arguments.arguments(LottoCount.from(3), PurchaseAmount.from(12000), 9)
        );
    }

    @Test
    void should_throwException_whenManualPurchasePriceExceedsTotalPrice() {
        LottoCount manualLottoCount = LottoCount.from(3);
        PurchaseAmount purchaseAmount = PurchaseAmount.from(2000);

        assertThatThrownBy(() -> TotalLottoCount.of(manualLottoCount, purchaseAmount))
                .isInstanceOf(InvalidLottoCountException.class);
    }
}
