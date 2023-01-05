package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoStatistics;

public class LottoTicketList {

    public static final String DELIMITER = "\n";

    private final List<LottoTicket> lottoTicketList;

    public LottoTicketList() {
        this(new ArrayList<>());
    }

    public LottoTicketList(List<LottoTicket> lottoTicketList) {
        this.lottoTicketList = lottoTicketList;
    }

    public void addAll(LottoTicketList lottoTicketList) {
        this.lottoTicketList.addAll(lottoTicketList.lottoTicketList);
    }

    public String getString() {
        return lottoTicketList.stream()
                .map(LottoTicket::getString)
                .collect(Collectors.joining(DELIMITER));
    }

    public LottoStatistics getStatistics(LottoWinningNumberList lottoWinningNumbers) {
        List<LottoResult> lottoResults = lottoTicketList.stream()
                .map(it -> it.getResult(lottoWinningNumbers))
                .collect(Collectors.toList());
        return new LottoStatistics(lottoResults);
    }
}
