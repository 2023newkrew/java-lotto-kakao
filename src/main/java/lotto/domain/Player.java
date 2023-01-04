package lotto.domain;

import java.util.List;

public class Player {

    private int money;
    private PlayerLottoResult playerLottoResult;
    private List<LottoTicket> lottoTickets;

    public Player(int money) {
        validatePlayerMoney(money);
        this.money = money;
    }

    private void validatePlayerMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("플레이어는 최소 0원을 가져야합니다.");
        }
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
