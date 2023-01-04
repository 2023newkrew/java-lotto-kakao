package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public void addLottoTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public LottoResult getLottoResult(LottoWinningNumber lottoWinningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            lottoResult.addLottoRankCount(
                    lottoWinningNumber.checkLottoRank(lottoTicket)
            );
        }
        return lottoResult;
    }

    public void resetLottoTickets() {
        lottoTickets.clear();
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
