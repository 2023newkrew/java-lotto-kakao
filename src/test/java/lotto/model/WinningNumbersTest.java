package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WinningNumbersTest {
    @Test
    @DisplayName("can validate incorrect winning numbers")
    void can_validate_incorrect_winning_numbers() {
        assertTrue(WinningNumbers.isValidBonusNumber(List.of(1, 2, 3, 4, 5, 6), 7));
        assertFalse(WinningNumbers.isValidBonusNumber(List.of(1, 2, 3, 4, 5, 6), 6));
        // The other cases are dependent on TicketTest
    }
}