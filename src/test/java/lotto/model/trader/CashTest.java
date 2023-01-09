package lotto.model.trader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashTest {
    @Test
    @DisplayName("can calculate purchasing ability")
    void can_calculate_purchasing_ability() {
        Cash cash = new Cash(10000L);
        assertEquals(10, cash.getPurchasingAbility(1000L));
        assertEquals(0, cash.getPurchasingAbility(10001L));
    }

    @Test
    @DisplayName("can validate overpayment")
    void can_validate_overpayment() {
        Cash cash = new Cash(10000L);
        assertThrows(IllegalArgumentException.class, () -> cash.pay(10001L));
    }

    @Test
    @DisplayName("can pay")
    void can_pay() {
        Cash cash = new Cash(10000L);
        cash.pay(1000L);
        assertEquals(9, cash.getPurchasingAbility(1000L));
    }
}
