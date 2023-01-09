package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoGame;
import domain.lotto.WinningNumbers;
import domain.lotto.generator.LottoNumberGenerator;
import domain.lotto.generator.RandomNumberGenerator;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTicketList;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        LottoTicketList lottoTicketList = createLottoTickets();
        OutputView.printLottoTickets(lottoTicketList);
        LottoGame lottoGame = new LottoGame(lottoTicketList, createWinningNumbers());
        LottoResults lottoResults = lottoGame.getLottoTicketsResult();
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTicketList createLottoTickets() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoTicketList lottoTicketList = new LottoTicketList();
        List<LottoTicket> manualLottoTicketList = new ArrayList<>(InputView.inputManualLottoNumbers(purchaseAmount));
        lottoTicketList.addManualLottoTickets(manualLottoTicketList);

        int manualCount = manualLottoTicketList.size();
        lottoTicketList.addAutoLottoTickets(purchaseAmount - manualCount);
        OutputView.printManualAndAutoCount(manualCount, purchaseAmount - manualCount);
        return lottoTicketList;
    }

    private WinningNumbers createWinningNumbers() {
        WinningNumbersDto winningNumbersDto = InputView.inputWinningNumbers();
        return new WinningNumbers(winningNumbersDto.getLottoNumbers(), winningNumbersDto.getBonusNumber());
    }
}
