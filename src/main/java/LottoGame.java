import java.util.HashMap;
import java.util.Map;

public class LottoGame {

    private final LottoTickets lottoTickets;
    private final WinningNumbers winningNumbers;

    public LottoGame(LottoTickets lottoTickets, WinningNumbers winningNumbers) {
        this.lottoTickets = lottoTickets;
        this.winningNumbers = winningNumbers;
    }

    public LottoResults getLottoTicketsResult() {
        LottoResults lottoResults = new LottoResults();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            lottoResults.countResult(LottoChecker.getResult(lottoTicket, winningNumbers));
        }
        return lottoResults;
    }
}
