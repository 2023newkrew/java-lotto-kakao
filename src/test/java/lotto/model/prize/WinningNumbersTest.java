package lotto.model.prize;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class WinningNumbersTest {
    @Test
    @DisplayName("can validate incorrect bonus number that overlaps with the winning")
    void can_validate_incorrect_bonus_number_that_overlaps_with_winning() {
        List<LottoNumber> winningNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(LottoNumber.get(i));
        }

        LottoTicket winning = new LottoTicket(winningNumbers);
        assertThrows(IllegalArgumentException.class, () -> new WinningNumbers(winning, LottoNumber.get(1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    @DisplayName("can count number of matches that of given ticket")
    void can_count_number_of_matches_that_of_given_ticket(int matchCount) {
        List<LottoNumber> winningNumbers = new ArrayList<>();
        List<LottoNumber> givenNumbers = new ArrayList<>();

        for (int i = 1; i <= matchCount; i++) {
            winningNumbers.add(LottoNumber.get(i));
            givenNumbers.add(LottoNumber.get(i));
        }

        for (int i = matchCount + 1; i <= 6; i++) {
            winningNumbers.add(LottoNumber.get(i + 10));
            givenNumbers.add(LottoNumber.get(i + 20));
        }

        WinningNumbers winning = new WinningNumbers(new LottoTicket(winningNumbers), LottoNumber.get(45));
        LottoTicket given = new LottoTicket(givenNumbers);
        assertEquals(matchCount, winning.matchNumbers(given));
    }

}