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
        Integer purchaseAmount = InputView.inputPurchaseAmount();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        LottoNumberGenerator lottoNumber = new LottoNumberGenerator();
        lottoTicketList.addAll(InputView.inputManualLottoNumbers(purchaseAmount));
        int manualCount = lottoTicketList.size();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < purchaseAmount - manualCount; i++) {
            lottoTicketList.add(new LottoTicket(lottoNumber.makeNumbers(randomNumberGenerator)));
        }
        return new LottoTicketList(lottoTicketList);
    }

    private WinningNumbers createWinningNumbers() {
        WinningNumbersDto winningNumbersDto = InputView.inputWinningNumbers();
        return new WinningNumbers(winningNumbersDto.getLottoNumbers(), winningNumbersDto.getBonusNumber());
    }
}
