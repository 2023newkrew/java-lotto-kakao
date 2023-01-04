package lotto.domain;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private LottoTicketList lottoTickets;

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
    }

    public void buy(int money) {
        lottoTickets = LottoDispenser.from(lottoSetting).getLottoTicket(money);
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
