package lotto.domain;

import lotto.domain.enumeration.LottoResult;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsManager {

    private final List<LottoTicket> lottoTickets;

    public LottoTicketsManager(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public String getLottoNumbersString() {
        return lottoTickets.stream()
                .map(LottoTicket::getString)
                .collect(Collectors.joining("\n"));
    }

    public LottoStatistics getStatistics(Lotto lotto) {
        List<LottoResult> lottoResults = lottoTickets.stream()
                .map(it -> it.getResult(lotto))
                .collect(Collectors.toList());
        return new LottoStatistics(lottoResults);
    }

    public int getCount() {
        return lottoTickets.size();
    }
}
