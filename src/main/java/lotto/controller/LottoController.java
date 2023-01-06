package lotto.controller;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public final class LottoController {
    private static LottoController instance;
    private final InputView inputView;
    private final OutputView outputView;

    private Money money;
    private LottoTickets tickets;

    private LottoController() {
        inputView = InputView.getInstance();
        outputView = OutputView.getInstance();
    }

    public static LottoController getInstance() {
        if (instance == null) {
            instance = new LottoController();
        }
        return instance;
    }

    public void run() {
        getMoney();
        getLottoTickets();
        WinningNumbers winningNumbers = getWinningNumbers();
        Result result = tickets.getResults(winningNumbers);
        outputView.printResultStatistics(result, money.getUsedMoney());
    }

    private void getMoney() {
        money = new Money(inputView.scanMoney());
    }

    private void getLottoTickets() {
        int manualLottoTicketsCount = getManualLottoTickets();
        getAutoLottoTickets();
        outputView.printLottoTickets(new LottoTicketsDto(tickets), manualLottoTicketsCount);
    }

    private int getManualLottoTickets() {
        int manualLottoTicketsCount = inputView.scanManualLottoTicketCount();
        tickets = new LottoTickets(
                new LottoTicketsDto(inputView.scanManualLottoTickets(manualLottoTicketsCount))
        );
        money = money.buyLottoTicketsAsManyAs(manualLottoTicketsCount);
        return manualLottoTicketsCount;
    }

    private int getAutoLottoTickets() {
        int autoLottoTicketsCount = money.getPurchasableCount();
        tickets.addAll(
                LottoTickets.automaticallyOf(autoLottoTicketsCount)
        );
        money = money.buyLottoTicketsConsumingAllLeftOver();
        return autoLottoTicketsCount;
    }

    private WinningNumbers getWinningNumbers() {
        return new WinningNumbers(
                new WinningNumbersDto(
                        new LottoTicketDto(inputView.scanWinningTicket()),
                        inputView.scanBonusNumber()
                )
        );
    }
}
