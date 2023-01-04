package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketList {

    private final List<LottoTicket> lottoTicketList;

    public LottoTicketList(List<LottoTicket> lottoTicketList) {
        this.lottoTicketList = lottoTicketList;
    }

    public String getLottoNumbersString() {
        return lottoTicketList.stream()
                .map(LottoTicket::getString)
                .collect(Collectors.joining("\n"));
    }

    public LottoStatistics getStatistics(LottoWinningNumberList lottoWinningNumbers) {
        List<LottoResult> lottoResults = lottoTicketList.stream()
                .map(it -> it.getResult(lottoWinningNumbers))
                .collect(Collectors.toList());
        return new LottoStatistics(lottoResults);
    }

    public int getCount() {
        return lottoTicketList.size();
    }
}
