package lotto.domain.game;

import java.util.List;
import lotto.domain.dispenser.LottoDispenser;
import lotto.domain.result.LottoIncomeRate;
import lotto.domain.result.LottoStatistics;
import lotto.domain.strategy.ManualNumberSelectStrategy;
import lotto.domain.strategy.NumberSelectStrategy;
import lotto.domain.strategy.RandomNumberSelectStrategy;
import lotto.domain.ticket.LottoTicketList;
import lotto.domain.ticket.LottoWinningNumberList;

public class LottoGame {

    private final LottoSetting lottoSetting;
    private final LottoDispenser lottoRandomDispenser;
    private final LottoTicketList lottoTickets = new LottoTicketList();
    private final LottoGameData lottoGameData = new LottoGameData();

    public LottoGame(LottoSetting lottoSetting) {
        this.lottoSetting = lottoSetting;
        this.lottoRandomDispenser = new LottoDispenser(lottoSetting,
                RandomNumberSelectStrategy.getInstance());
    }

    public void buyRandomly(int money) {
        addRandomTicketCount(buy(lottoRandomDispenser, money));
    }

    public void buyManually(int money, List<List<Integer>> numbers) {
        NumberSelectStrategy numberSelectStrategy = new ManualNumberSelectStrategy(numbers);
        LottoDispenser lottoManualDispenser
                = new LottoDispenser(lottoSetting, numberSelectStrategy);
        addManualTicketCount(buy(lottoManualDispenser, money));
    }

    private int buy(LottoDispenser lottoDispenser, int money) {
        LottoTicketList lottoTicketList = lottoDispenser.getLottoTicketList(money);
        lottoTickets.addAll(lottoTicketList);
        lottoGameData.saveLeftoverMoney(lottoDispenser.receiveLeftoverMoney());
        return lottoTicketList.getCount();
    }

    public void addManualTicketCount(int count) {
        lottoGameData.addManualTicketCount(count);
    }

    public void addRandomTicketCount(int count) {
        lottoGameData.addRandomTicketCount(count);
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
