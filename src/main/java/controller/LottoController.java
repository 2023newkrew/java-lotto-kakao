package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.LottoGame;
import domain.lotto.number.WinningNumbers;
import domain.lotto.number.LottoNumberMaker;
import domain.lotto.number.NumberGeneratable;
import domain.lotto.number.RandomNumberGenerator;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTicket;
import domain.lotto.ticket.LottoTickets;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        LottoTickets lottoTickets = createLottoTickets(InputView.inputPurchaseAmount(), new RandomNumberGenerator());
        OutputView.printLottoTickets(lottoTickets);
        LottoGame lottoGame = new LottoGame(lottoTickets, createWinningNumbers());
        LottoResults lottoResults = lottoGame.getLottoTicketsResult();
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTickets createLottoTickets(final Integer purchaseAmount, final NumberGeneratable numberGenerator) {
        LottoNumberMaker lottoNumberMaker = new LottoNumberMaker();
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(new LottoTicket(lottoNumberMaker.makeNumbers(numberGenerator)));
        }
        return new LottoTickets(lottoTicketList);
    }

    private WinningNumbers createWinningNumbers() {
        WinningNumbers winningNumbers = null;
        while (winningNumbers == null) {
            winningNumbers = tryCreateWinningNumbers();
        }
        return winningNumbers;
    }

    private WinningNumbers tryCreateWinningNumbers() {
        try {
            WinningNumbersDto winningNumbersDto = InputView.inputWinningNumbers();
            return new WinningNumbers(winningNumbersDto.getLottoNumbers(), winningNumbersDto.getBonusNumber());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
