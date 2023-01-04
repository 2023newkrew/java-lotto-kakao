package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        ResultView resultView = ResultView.getInstance();

        Money money = new Money(inputView.scanMoney());

        int manualTicketCount = inputView.scanManualLottoTicketCount();
        LottoTickets tickets = new LottoTickets(
                new LottoTicketsDto(inputView.scanManualLottoTickets(manualTicketCount))
        );
        money = money.buyLottoTicketsAsManyAs(manualTicketCount);

        int autoTicketCount = money.getPurchasableCount();
        tickets.addAll(
                LottoTickets.automaticallyOf(autoTicketCount)
        );
        money = money.buyLottoTicketsConsumingAllLeftOver();

        resultView.printLottoTickets(new LottoTicketsDto(tickets), manualTicketCount);

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
