package lotto.domain;

import java.util.List;

public class Player {

    private Money money;
    private PlayerLottoResult playerLottoResult;
    private List<LottoTicket> lottoTickets;

    public Player(Money money) {
        this.money = money;
    }

    public void buyLottoTickets(Seller seller) {
        this.lottoTickets = seller.sellLottoTickets(money);
        Money spentMoney = seller.calculateTotalPrice(lottoTickets);
        playerLottoResult = new PlayerLottoResult(spentMoney);
        this.money = money.subtract(spentMoney);
    }

    public PlayerLottoResult findResult(WinnerCompareRule winnerCompareRule) {
        for (LottoTicket lottoTicket : lottoTickets) {
            playerLottoResult.addResult(winnerCompareRule.compare(lottoTicket));
        }
        return playerLottoResult;
    }
}
