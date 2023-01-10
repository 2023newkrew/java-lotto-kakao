package lotto.domain.lotto.ticket.generator;

import lotto.domain.lotto.LottoNumber;

import java.util.List;

public interface LottoTicketGenerator {
    List<LottoNumber> generate();
}
