package lotto.model.service;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottoPublisherTest {
    @Test
    @DisplayName("can publish manual lotto from given numbers list")
    void can_publish_manual_lotto_from_given_numbers_list() {
        LottoPublisher lottoPublisher = new LottoPublisher();
        List<List<LottoNumber>> givenNumbers = new ArrayList<>();

        givenNumbers.add(List.of(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        ));
        givenNumbers.add(List.of(
                LottoNumber.get(7),
                LottoNumber.get(8),
                LottoNumber.get(9),
                LottoNumber.get(10),
                LottoNumber.get(11),
                LottoNumber.get(12)
        ));

        LottoTickets tickets = lottoPublisher.publishManualLotto(givenNumbers);
        assertEquals(2, tickets.size());
        assertEquals("[1, 2, 3, 4, 5, 6]\n[7, 8, 9, 10, 11, 12]\n", tickets.toString());
    }
}
