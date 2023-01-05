package lotto.domain;

import java.util.List;
import lotto.domain.strategy.ManualNumberSelectStrategy;
import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.strategy.RandomNumberSelectStrategy;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private final LottoDispenser lottoRandomDispenser;
    private final LottoTicketList lottoTickets = new LottoTicketList();
    private final LottoGameData lottoGameData = new LottoGameData();

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
        this.lottoRandomDispenser = new LottoDispenser(lottoSetting, RandomNumberSelectStrategy.getInstance());
    }

    public void buyRandomly(int money) {
        LottoTicketList lottoTicketList = lottoRandomDispenser.getLottoTicketList(money);
        lottoTickets.addAll(lottoTicketList);
        lottoGameData.saveLeftoverMoney(lottoRandomDispenser.receiveLeftoverMoney());
        lottoGameData.addRandomTicketCount(lottoTicketList.getCount());
    }

    public void buyManually(int money, List<List<Integer>> numbers) {
        NumberSelectStrategy numberSelectStrategy = new ManualNumberSelectStrategy(numbers);
        LottoDispenser lottoDispenser = new LottoDispenser(lottoSetting, numberSelectStrategy);
        LottoTicketList lottoTicketList = lottoDispenser.getLottoTicketList(money);
        lottoTickets.addAll(lottoTicketList);
        lottoGameData.saveLeftoverMoney(lottoDispenser.receiveLeftoverMoney());
        lottoGameData.addManualTicketCount(lottoTicketList.getCount());
    }

    public int receiveLeftoverMoney() {
        return lottoGameData.receiveLeftoverMoney();
    }

    public int getManualTicketCount() {
        return lottoGameData.getManualTicketCount();
    }

    public int getRandomTicketCount() {
        return lottoGameData.getRandomTicketCount();
    }

    public String getLottoTicketsString() {
        return lottoTickets.getString();
    }

    public String getWinningString(LottoWinningNumberList lottoWinningNumbers) {
        LottoStatistics lottoStatistics = lottoTickets.getStatistics(lottoWinningNumbers);
        LottoIncomeRate lottoIncomeRate = new LottoIncomeRate(lottoStatistics,
                lottoSetting.getLottoTicketPrice());
        return lottoStatistics.getString() + "\n"
                + lottoIncomeRate.getString();
    }
}
