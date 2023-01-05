package lotto.domain;

import lotto.strategy.AutoNumberSelectStrategy;

import java.util.List;

public class LottoGame {

    private final LottoDispenser lottoDispenser;
    private LottoTicketsManager lottoTicketsManager;

    public LottoGame(AutoNumberSelectStrategy autoNumberSelectStrategy) {
        this.lottoDispenser = new LottoDispenser(autoNumberSelectStrategy);
    }

    public void buyLottoTickets(long money, List<LottoNumberList> manualLottoNumberList) {
        lottoTicketsManager = lottoDispenser.getLottoTickets(money, manualLottoNumberList);
    }

    public String getLottoTicketsString() {
        return lottoTicketsManager.getLottoNumbersString();
    }

    public int getQuantityOfLottoTickets() {
        return lottoTicketsManager.getSize();
    }

    public String getLottoGameResult(WinningLotto winningLotto) {
        return lottoTicketsManager.getStatistics(winningLotto).toString();
    }
}
