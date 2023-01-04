package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        ResultView resultView = ResultView.getInstance();

        int money = inputView.scanMoney();
        int count = money / 1000;
        money = count * 1000;

        LottoTickets tickets = LottoTickets.automaticallyOf(count);
        resultView.printLottoTickets(tickets);

        LottoTicket winningTicket = LottoTicket.fromIntegerList(inputView.scanWinningTicket());
        LottoNumber bonusNumber = LottoNumber.valueOf(inputView.scanBonusNumber());

        WinningNumbers winningNumbers = new WinningNumbers(
                winningTicket,
                bonusNumber
        );

        Result result = tickets.getResults(winningNumbers);
        resultView.printResultStatistics(result, money);
    }
}
