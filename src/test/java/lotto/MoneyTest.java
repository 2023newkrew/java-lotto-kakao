package lotto;

import lotto.model.Money;
import lotto.model.errors.LottoOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class MoneyTest {

    @Test
    @DisplayName("돈이 음수가 아니면 정상 작동")
    void should_returnValue_when_valueIsPositive() {
        Money money = new Money();
        money.add(100).spend(5).spend(10);
        assertEquals(money.getValue(), 85);
    }

    @Test
    @DisplayName("돈이 음수가 되면 예외 발생")
    void should_throwException_when_givenMinusValue() {
        Money money = new Money();
        assertThrowsExactly(LottoOutOfRangeException.class, () -> money.add(100).spend(5).spend(100));
    }
}
