package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Money initialMoney;
    private Money currentMoney;
    private PlayerLottoResult playerLottoResult;
    private List<LottoTicket> lottoTickets = new ArrayList<>();

    public Player(Money money) {
        this.initialMoney = money;
        this.currentMoney = money;
    }

    public void buyManualLottoTickets(Seller seller, List<LottoTicket> manualLottoTickets) throws IllegalArgumentException {
        seller.checkHasEnoughMoneyForManualLottoTickets(manualLottoTickets.size(), currentMoney);

        Money spentMoney = seller.calculateTotalPrice(manualLottoTickets);
        this.currentMoney = currentMoney.subtract(spentMoney);

        lottoTickets.addAll(manualLottoTickets);
    }

    public void buyLottoTickets(Seller seller) {
        lottoTickets.addAll(seller.sellAutoLottoTickets(currentMoney));
        Money spentMoney = seller.calculateTotalPrice(lottoTickets);
        this.currentMoney = currentMoney.subtract(spentMoney);
    }

    public void initPlayerLottoResult() {
        Money totalSpentMoney = initialMoney.subtract(currentMoney);
        playerLottoResult = new PlayerLottoResult(totalSpentMoney);
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
