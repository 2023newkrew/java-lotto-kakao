package lotto.controller;

import lotto.dto.LottoTicketsDto;
import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

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
        outputView.printLottoTickets(makeLottoTicketsDto(tickets), manualLottoTicketsCount);
    }

    private LottoTicketsDto makeLottoTicketsDto(LottoTickets tickets) {
        return new LottoTicketsDto(tickets.getTickets().stream()
                .map(ticket -> {
                    return ticket.getNumbers().stream()
                            .map(LottoNumber::getValue)
                            .collect(Collectors.toList());
                }).collect(Collectors.toList())
        );
    }

    private int getManualLottoTickets() {
        int manualLottoTicketsCount = inputView.scanManualLottoTicketCount();
        money = money.buyLottoTicketsAsManyAs(manualLottoTicketsCount);
        tickets = LottoTickets.fromNumberLists(
                inputView.scanManualLottoTickets(manualLottoTicketsCount)
        );
        return manualLottoTicketsCount;
    }

    private int getAutoLottoTickets() {
        int autoLottoTicketsCount = money.getPurchasableCount();
        money = money.buyLottoTicketsConsumingAllLeftOver();
        tickets.addAll(
                LottoTickets.automaticallyOf(autoLottoTicketsCount)
        );
        return autoLottoTicketsCount;
    }

    private WinningNumbers getWinningNumbers() {
        return new WinningNumbers(
                LottoTicket.fromNumbers(inputView.scanWinningTicket()),
                LottoNumber.valueOf(inputView.scanBonusNumber())
        );
    }
}
