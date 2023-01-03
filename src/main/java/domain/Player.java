package domain;

import java.util.List;

public class Player {

    private Money money;
    private List<LottoTicket> lottoTickets;

    public Player(Money money) {
        this.money = money;
    }

    public void buyLottoTickets(Seller seller) {
        this.lottoTickets = seller.sellLottoTickets(money);
    }
}
