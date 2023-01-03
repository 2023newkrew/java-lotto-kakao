package lotto.domain;

import lotto.strategy.NumberSelectStrategy;

public class LottoGame {

    private final LottoDispenser lottoDispenser;
    private LottoTicketsManager lottoTicketsManager;

    public LottoGame(NumberSelectStrategy numberSelectStrategy) {
        this.lottoDispenser = new LottoDispenser(numberSelectStrategy);
    }

    public void buy(int money) {
        lottoTicketsManager = lottoDispenser.getLottoTicket(money);
    }

    public String getLottoTicketsString() {
        return lottoTicketsManager.getLottoNumbersString();
    }

    public int getCountOfLottoTickets() {
        return lottoTicketsManager.getCount();
    }

    public String getWinningString(Lotto lotto) {
        return lottoTicketsManager.getStatistics(lotto).getString();
    }
}
