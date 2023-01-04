package lotto.domain;

import java.util.List;

public class Player {

    private int money;
    private PlayerLottoResult playerLottoResult;
    private List<LottoTicket> lottoTickets;

    public Player(int money) {
        this.money = money;
    }

    public void buyLottoTickets(LottoTicketSeller seller) {
        this.lottoTickets = seller.sellLottoTickets(money);
        int spentMoney = seller.calculateTotalPrice(lottoTickets);
        playerLottoResult = new PlayerLottoResult(spentMoney);
        this.money = money - spentMoney;
    }

    public PlayerLottoResult findResult(WinnerCompareRule winnerCompareRule) {
        for (LottoTicket lottoTicket : lottoTickets) {
            playerLottoResult.addResult(winnerCompareRule.compare(lottoTicket));
        }
        return playerLottoResult;
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
