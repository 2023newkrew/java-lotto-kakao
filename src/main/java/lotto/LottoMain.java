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
        resultView.printLottoTickets(new LottoTicketsDto(tickets));

        WinningNumbers winningNumbers = new WinningNumbers(
                new WinningNumbersDto(
                        new LottoTicketDto(inputView.scanWinningTicket()),
                        inputView.scanBonusNumber()
                )
        );

        Result result = tickets.getResults(winningNumbers);
        resultView.printResultStatistics(result, money);
    }
}
