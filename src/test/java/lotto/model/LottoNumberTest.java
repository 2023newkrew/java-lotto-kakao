package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {
    @Test
    @DisplayName("can validate incorrect range of number")
    void can_validate_incorrect_range_of_numbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(LottoNumber.NUMBER_RANGE + 1);
        });
    }
}
