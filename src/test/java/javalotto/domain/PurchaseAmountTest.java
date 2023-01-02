package javalotto.domain;

import javalotto.exception.AmountNotMultipleOfUnitPriceException;
import javalotto.exception.AmountOutOfRangeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, Integer.MAX_VALUE / 1000 * 1000})
    public void should_createSuccessfully_when_validPurchaseAmount(int purchaseAmount) {
        PurchaseAmount createdPurchaseAmount = PurchaseAmount.from(purchaseAmount);

        Assertions.assertThat(createdPurchaseAmount).isNotNull();
    }

    @Test
    public void should_throwException_when_amountIsNotMultipleOfUnitPrice() {
        int amount = 1234;

        Assertions.assertThatThrownBy(() -> PurchaseAmount.from(amount))
                .isInstanceOf(AmountNotMultipleOfUnitPriceException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, 0, 900, Integer.MAX_VALUE + 1})
    public void should_throwException_when_amountIsInInvalidRange(int amount) {
        Assertions.assertThatThrownBy(() -> PurchaseAmount.from(amount))
                .isInstanceOf(AmountOutOfRangeException.class);
    }
}