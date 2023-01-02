package lotto.model;
import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public void addLottoTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public LottoResult getLottoResult(LottoWinningNumber lottoWinningNumber) {
        LottoResult lottoResult = new LottoResult();
        for (LottoTicket lottoTicket : lottoTickets) {
            lottoResult.addLottoRank(lottoWinningNumber.checkRank(lottoTicket));
        }
        return lottoResult;
    }
}
