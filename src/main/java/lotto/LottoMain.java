package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        ResultView resultView = ResultView.getInstance();

        int money = inputView.getMoneyInput();
        int count = money / 1000;
        money = count * 1000;

        LottoTickets tickets = LottoTickets.of(count);
        resultView.printLottoTickets(tickets);

        WinningNumbers winningNumbers = new WinningNumbers(
                inputView.getWinningNumbers(),
                inputView.getBonusNumber()
        );

        Result result = tickets.getResults(winningNumbers);
        resultView.printResultStatistics(result, money);
    }
}
