package lotto.model.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CashTest {
    @Test
    @DisplayName("can validate overpayment")
    void can_validate_overpayment() {
        Cash cash = new Cash(10000L);
        assertThrows(IllegalArgumentException.class, () -> cash.pay(new Cash(10001L)));
    }
}
