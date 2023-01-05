package lotto.domain;

import lotto.strategy.NumberSelectStrategy;

public class LottoGame {

    private final LottoDispenser lottoDispenser;
    private LottoTicketsManager lottoTicketsManager;

    public LottoGame(NumberSelectStrategy numberSelectStrategy) {
        this.lottoDispenser = new LottoDispenser(numberSelectStrategy);
    }

    public void buyLottoTickets(long money) {
        lottoTicketsManager = lottoDispenser.getLottoTicket(money);
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
