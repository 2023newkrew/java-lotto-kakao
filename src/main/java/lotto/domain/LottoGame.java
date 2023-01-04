package lotto.domain;

import lotto.domain.strategy.RandomNumberSelectStrategy;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private final LottoDispenser lottoRandomDispenser;

    private LottoTicketList lottoTickets;

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
        this.lottoRandomDispenser = new LottoDispenser(lottoSetting, RandomNumberSelectStrategy.getInstance());
    }

    public void buy(int money) {
        lottoTickets = lottoRandomDispenser.getLottoTicketList(money);
    }

    public String getLottoTicketsString() {
        return lottoTickets.getString();
    }

    public int getCountOfLottoTickets() {
        return lottoTickets.getCount();
    }

    public String getWinningString(LottoWinningNumberList lottoWinningNumbers) {
        LottoStatistics lottoStatistics = lottoTickets.getStatistics(lottoWinningNumbers);
        LottoIncomeRate lottoIncomeRate = new LottoIncomeRate(lottoStatistics,
                lottoSetting.getLottoTicketPrice());
        return lottoStatistics.getString() + "\n"
                + lottoIncomeRate.getString();
    }
}
