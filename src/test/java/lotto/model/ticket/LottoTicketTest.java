package lotto.model.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LottoTicketTest {
    @Test
    @DisplayName("can validate incorrect length of numbers")
    void can_validate_incorrect_length_of_numbers() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i < LottoTicket.NUMBERS_LENGTH; i++) {
            numbers.add(lottoNumbers.get(i));
        }
        assertThrows(IllegalArgumentException.class, () -> new LottoTicket(numbers));

        numbers.add(lottoNumbers.get(LottoTicket.NUMBERS_LENGTH));
        numbers.add(lottoNumbers.get(LottoTicket.NUMBERS_LENGTH + 1));
        assertThrows(IllegalArgumentException.class, () -> new LottoTicket(numbers));
    }

    @Test
    @DisplayName("can validate duplication of numbers")
    void can_validate_duplication_of_numbers() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 1; i < LottoTicket.NUMBERS_LENGTH; i++) {
            numbers.add(lottoNumbers.get(i));
        }
        numbers.add(lottoNumbers.get(1));

        assertThrows(IllegalArgumentException.class, () -> new LottoTicket(numbers));
    }
}