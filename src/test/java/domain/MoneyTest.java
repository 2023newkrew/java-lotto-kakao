package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    void moneyCreateTest() {
        int biggerThanIntegerMoney = Integer.MAX_VALUE + 1;
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(biggerThanIntegerMoney));

        int validMoney = 20_000;
        Assertions.assertThatCode(() -> new Money(validMoney))
                .doesNotThrowAnyException();
    }
}
