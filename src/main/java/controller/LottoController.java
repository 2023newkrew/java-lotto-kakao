package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;
import domain.lotto.number.LottoNumbersMaker;
import domain.lotto.number.generator.LottoNumbersGenerator;
import domain.lotto.number.generator.NumbersGeneratable;
import domain.lotto.result.LottoResults;
import domain.lotto.ticket.LottoTickets;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        LottoTickets lottoTickets = createLottoTickets(InputView.inputPurchaseAmount(), new LottoNumbersGenerator());
        OutputView.printLottoTickets(lottoTickets);
        LottoResults lottoResults = new LottoResults(lottoTickets, createWinningNumbers());
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTickets createLottoTickets(final int purchaseAmount, final NumbersGeneratable numbersGenerator) {
        LottoNumbersMaker lottoNumberMaker = new LottoNumbersMaker();
        List<LottoNumbers> lottoTicketList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(new LottoNumbers(lottoNumberMaker.makeNumbers(numbersGenerator)));
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
