package lotto.domain;

import java.util.List;

public class Player {

    private int money;
    private PlayerLottoResult playerLottoResult;
    private List<LottoTicket> manualLottoTickets;
    private List<LottoTicket> autoLottoTickets;

    public Player(int money) {
        validatePlayerMoney(money);
        this.money = money;
        this.playerLottoResult = new PlayerLottoResult();
    }

    private void validatePlayerMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("플레이어는 최소 0원을 가져야합니다.");
        }
    }

    public void buyManualLottoTickets(LottoTicketSeller seller, List<List<Integer>> manualLottoNumbers) {
        this.manualLottoTickets = seller.sellManualLottoTickets(manualLottoNumbers);
        calculateMoneyLeft(seller, manualLottoTickets);
    }

    private void calculateMoneyLeft(LottoTicketSeller seller, List<LottoTicket> lottoTickets) {
        int spentMoney = seller.calculateTotalPrice(lottoTickets);
        playerLottoResult.addSpentMoney(spentMoney);
        this.money -= spentMoney;
    }

    public void buyAutoLottoTickets(LottoTicketSeller seller) {
        this.autoLottoTickets = seller.sellAutoLottoTickets(money);
        calculateMoneyLeft(seller, autoLottoTickets);
    }

    public PlayerLottoResult findResult(WinnerCompareRule winnerCompareRule) {
        for (LottoTicket manualLottoTicket : manualLottoTickets) {
            playerLottoResult.addResult(winnerCompareRule.compare(manualLottoTicket));
        }
        for (LottoTicket autoLottoTicket : autoLottoTickets) {
            playerLottoResult.addResult(winnerCompareRule.compare(autoLottoTicket));
        }
        return playerLottoResult;
    }

    public List<LottoTicket> getManualLottoTickets() {
        return manualLottoTickets;
    }

    public List<LottoTicket> getAutoLottoTickets() {
        return autoLottoTickets;
    }
}
