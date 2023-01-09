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
        setMoney();
        setLottoTickets();
        WinningNumbers winningNumbers = getWinningNumbers();
        Result result = tickets.getResults(winningNumbers);
        outputView.printResultStatistics(result, money.getUsedMoney());
    }

    private void setMoney() {
        try {
            money = new Money(inputView.scanMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            setMoney();
        }
    }

    private void setLottoTickets() {
        int manualLottoTicketsCount = setManualLottoTickets();
        setAutoLottoTickets();
        outputView.printLottoTickets(makeLottoTicketsDto(tickets), manualLottoTicketsCount);
    }

    private LottoTicketsDto makeLottoTicketsDto(LottoTickets tickets) {
        return new LottoTicketsDto(tickets.getTickets().stream()
                .map(ticket -> ticket.getNumbers().stream()
                        .map(LottoNumber::getValue)
                        .collect(Collectors.toList())
                ).collect(Collectors.toList())
        );
    }

    private int setManualLottoTickets() {
        int manualLottoTicketsCount = getManualLottoTicketsCount();
        setManualLottoTicketsOf(manualLottoTicketsCount);
        return manualLottoTicketsCount;
    }

    private int getManualLottoTicketsCount() {
        try {
            int manualLottoTicketsCount = inputView.scanManualLottoTicketCount();
            money = money.buyLottoTicketsAsManyAs(manualLottoTicketsCount);
            return manualLottoTicketsCount;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getManualLottoTicketsCount();
        }
    }

    private void setManualLottoTicketsOf(int count) {
        try {
            tickets = LottoTickets.fromNumberLists(
                    inputView.scanManualLottoTickets(count)
            );
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            setManualLottoTicketsOf(count);
        }
    }

    private int setAutoLottoTickets() {
        try {
            int autoLottoTicketsCount = money.getPurchasableCount();
            money = money.buyLottoTicketsConsumingAllLeftOver();
            tickets.addAll(
                    LottoTickets.automaticallyOf(autoLottoTicketsCount)
            );
            return autoLottoTicketsCount;
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return setAutoLottoTickets();
        }
    }

    private WinningNumbers getWinningNumbers() {
        try {
            return new WinningNumbers(
                    LottoTicket.fromNumbers(inputView.scanWinningTicket()),
                    LottoNumber.valueOf(inputView.scanBonusNumber())
            );
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }
}
