package lotto.model.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class LottoNumbersTest {
    @Test
    @DisplayName("can return a LottoNumber that matches given number")
    void can_return_a_LottoNumber_that_matches_given_number() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        for (int i = 1; i <= LottoNumber.NUMBER_RANGE; i++) {
            assertEquals(i, lottoNumbers.get(i).getNumber());
        }
    }
}
