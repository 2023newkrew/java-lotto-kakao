package lotto.model.service;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;

public class TicketGenerator {
    private final RandomNumbersGenerator randomNumbersGenerator;

    public TicketGenerator() {
        this.randomNumbersGenerator = new RandomNumbersGenerator();
    }

    public LottoTicket generateTicket() {
        return new LottoTicket(this.randomNumbersGenerator.getOrderedNumbers());
    }

    public LottoTicket generateTicket(List<LottoNumber> numbers) {
        return new LottoTicket(numbers);
    }
}
