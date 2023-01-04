package domain;

import java.util.List;

public interface LottoTicketGenerator {
    LottoTicket generate();
    List<LottoTicket> generate(int lottoCount);
}
