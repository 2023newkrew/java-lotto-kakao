package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketDto {
    private final List<Integer> ticket;

    public LottoTicketDto(LottoTicket lottoTicket) {
        this.ticket = lottoTicket.stream().map(
                LottoNumber::getValue
        ).collect(Collectors.toList());
    }

    public LottoTicketDto(List<Integer> numbers) {
        this.ticket = numbers;
    }

    public List<Integer> getTicket() {
        return ticket;
    }
}
