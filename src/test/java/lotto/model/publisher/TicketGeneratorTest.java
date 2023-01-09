package lotto.model.publisher;

import lotto.model.publisher.manual.ManualTicketingStrategy;
import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketGeneratorTest {
    @Test
    @DisplayName("can generate ticket from given numbers")
    void can_generate_ticket_from_given_numbers() {
        TicketGenerator manualTicketGenerator = new TicketGenerator(new ManualTicketingStrategy());
        List<LottoNumber> givenNumbers = List.of(
                LottoNumber.get(1),
                LottoNumber.get(2),
                LottoNumber.get(3),
                LottoNumber.get(4),
                LottoNumber.get(5),
                LottoNumber.get(6)
        );

        LottoTicket ticket = manualTicketGenerator.generate(givenNumbers);
        assertEquals("[1, 2, 3, 4, 5, 6]", ticket.toString());
    }
}
