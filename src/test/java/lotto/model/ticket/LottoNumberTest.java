package lotto.model.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottoNumberTest {
    @Test
    @DisplayName("can return a LottoNumber that matches given number")
    void can_return_a_LottoNumber_that_matches_given_number() {
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            assertEquals(i, LottoNumber.get(i).getNumber());
            assertEquals(i, LottoNumber.get(String.valueOf(i)).getNumber());
        }
    }

    @Test
    @DisplayName("can validate incorrect range of number")
    void can_validate_incorrect_range_of_numbers() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.get(0));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.get(LottoNumber.NUMBER_RANGE + 1));
    }

    @Test
    @DisplayName("can validate incorrect string number")
    void can_validate_incorrect_string_numbers() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.get("0"));
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.get(String.valueOf(LottoNumber.NUMBER_RANGE + 1)));
        assertThrows(NumberFormatException.class, () -> LottoNumber.get("a"));
    }
}
