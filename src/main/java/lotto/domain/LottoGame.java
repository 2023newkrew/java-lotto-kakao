package lotto.domain;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private LottoTicketList lottoTickets;

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
    }

    public void buy(int money) {
        lottoTickets = LottoDispenser.from(lottoSetting.getNumberSelectStrategy())
                .getLottoTicket(money);
    }

    public String getLottoTicketsString() {
        return lottoTickets.getLottoNumbersString();
    }

    public int getCountOfLottoTickets() {
        return lottoTickets.getCount();
    }

    public String getWinningString(LottoWinningNumberList lottoWinningNumbers) {
        return lottoTickets.getStatistics(lottoWinningNumbers).getString();
    }
}
