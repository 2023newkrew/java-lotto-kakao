package lotto;

public class LottoGame {

    private final LottoDispenser lottoDispenser;
    private LottoTickets lottoTickets;

    public LottoGame(NumberSelectStrategy numberSelectStrategy) {
        this.lottoDispenser = new LottoDispenser(numberSelectStrategy);
    }

    public void buy(int money) {
        lottoTickets = lottoDispenser.getLottoTicket(money);
    }

    public String getLottoTicketsString() {
        return lottoTickets.getLottoNumbersString();
    }

    public int getCountOfLottoTickets() {
        return lottoTickets.getCount();
    }

    public String getWinningString(LottoWinningNumbers lottoWinningNumbers) {
        return lottoTickets.getStatistics(lottoWinningNumbers).getString();
    }
}
