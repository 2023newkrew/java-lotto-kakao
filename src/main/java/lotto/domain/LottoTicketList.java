package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketList {

    private final List<LottoTicket> lottoTickets;

    public LottoTicketList(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public String getLottoNumbersString() {
        return lottoTickets.stream()
                .map(LottoTicket::getString)
                .collect(Collectors.joining("\n"));
    }

    public LottoStatistics getStatistics(LottoWinningNumberList lottoWinningNumbers) {
        List<LottoResult> lottoResults = lottoTickets.stream()
                .map(it -> it.getResult(lottoWinningNumbers))
                .collect(Collectors.toList());
        return new LottoStatistics(lottoResults);
    }

    public int getCount() {
        return lottoTickets.size();
    }
}
