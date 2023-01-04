package lotto.domain;

import java.util.List;
import lotto.domain.strategy.ManualNumberSelectStrategy;
import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.strategy.RandomNumberSelectStrategy;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private final LottoDispenser lottoRandomDispenser;
    private final LottoTicketList lottoTickets = new LottoTicketList();
    private int leftoverMoney = 0;

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
        this.lottoRandomDispenser = new LottoDispenser(lottoSetting, RandomNumberSelectStrategy.getInstance());
    }

    public void buyRandom(int money) {
        lottoTickets.addAll(lottoRandomDispenser.getLottoTicketList(money));
        saveLeftoverMoney(lottoRandomDispenser.receiveLeftoverMoney());
    }

    public void buyManually(int money, List<List<Integer>> numbers) {
        NumberSelectStrategy numberSelectStrategy = new ManualNumberSelectStrategy(numbers);
        LottoDispenser lottoDispenser = new LottoDispenser(lottoSetting, numberSelectStrategy);
        lottoTickets.addAll(lottoDispenser.getLottoTicketList(money));
        saveLeftoverMoney(lottoDispenser.receiveLeftoverMoney());
    }

    private void saveLeftoverMoney(int leftoverMoney) {
        this.leftoverMoney += leftoverMoney;
    }

    public int receiveLeftoverMoney() {
        int leftoverMoney = this.leftoverMoney;
        this.leftoverMoney = 0;
        return leftoverMoney;
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
