package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        ResultView resultView = ResultView.getInstance();

        Money money = new Money(inputView.scanMoney());

        LottoTickets tickets = LottoTickets.automaticallyOf(money.getPurchasableCount());
        resultView.printLottoTickets(new LottoTicketsDto(tickets));

        WinningNumbers winningNumbers = new WinningNumbers(
                new WinningNumbersDto(
                        new LottoTicketDto(inputView.scanWinningTicket()),
                        inputView.scanBonusNumber()
                )
        );

        Result result = tickets.getResults(winningNumbers);
        resultView.printResultStatistics(result, money.getUsedMoney());
    }
}
