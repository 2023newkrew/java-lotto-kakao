package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    public static void main(String[] args) {
        InputView iv = InputView.getInstance();
        ResultView rv = ResultView.getInstance();
        int money = iv.getMoneyInput();
        int count = money / 1000;
        money = count * 1000;

        LottoTickets tickets = LottoTickets.countOf(count);
        rv.printCountOfLottoTickets(tickets.getTicket().size());
        rv.printLottoTickets(tickets);

        String winningNumbers,bonusNumber;
        winningNumbers = iv.getWinningNumbers();
        bonusNumber = iv.getBonusNumber();

        WinningNumbers wn = new WinningNumbers(winningNumbers, bonusNumber);

        Result result = tickets.getResults(wn);
        rv.printResultStatistics(result, money);
    }
}
