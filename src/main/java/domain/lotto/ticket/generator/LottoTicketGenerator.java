package domain.lotto.ticket.generator;

import domain.lotto.LottoNumber;

import java.util.List;

public interface LottoTicketGenerator {
    List<LottoNumber> generate();
}
