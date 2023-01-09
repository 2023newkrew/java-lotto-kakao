package controller;

import domain.dto.WinningNumbersDto;
import domain.lotto.number.LottoNumbers;
import domain.lotto.number.WinningNumbers;
import domain.lotto.number.generator.LottoNumbersGenerator;
import domain.lotto.number.generator.NumbersGeneratable;
import domain.lotto.result.LottoResults;
import domain.lotto.number.LottoTickets;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void start() {
        int totalPurchaseAmount = InputView.inputAutoPurchaseAmount();
        int manualPurchaseAmount = InputView.inputManualPurchaseAmount(totalPurchaseAmount);
        int autoPurchaseAmount = totalPurchaseAmount - manualPurchaseAmount;
        LottoTickets lottoTickets = createTotalLottoTickets(autoPurchaseAmount, manualPurchaseAmount);
        OutputView.printAutoAmountAndManualAmount(autoPurchaseAmount, manualPurchaseAmount);
        OutputView.printLottoTickets(lottoTickets);
        LottoResults lottoResults = new LottoResults(lottoTickets, createWinningNumbers());
        OutputView.printLottoResults(lottoResults);
    }

    private LottoTickets createTotalLottoTickets(final int autoPurchaseAmount, final int manualPurchaseAmount) {
        LottoTickets lottoTickets = createManualLottoTickets(manualPurchaseAmount);
        lottoTickets.addTickets(createAutoLottoTickets(autoPurchaseAmount, new LottoNumbersGenerator()));
        return lottoTickets;
    }

    private LottoTickets createAutoLottoTickets(final int purchaseAmount, final NumbersGeneratable numbersGenerator) {
        List<LottoNumbers> lottoTicketList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(LottoNumbers.create(numbersGenerator));
        }
        return new LottoTickets(lottoTicketList);
    }

    private LottoTickets createManualLottoTickets(final int purchaseAmount) {
        if (purchaseAmount < 1) return new LottoTickets(new ArrayList<>());
        List<LottoNumbers> lottoTicketList = new ArrayList<>();
        OutputView.printStartInputManualLottoNumbersMessage();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoTicketList.add(createManualLottoTicket());
        }
        return new LottoTickets(lottoTicketList);
    }

    private LottoNumbers createManualLottoTicket() {
        LottoNumbers lottoNumbers = null;
        while (lottoNumbers == null) {
            lottoNumbers = tryCreateManualLottoTicket();
        }
        return lottoNumbers;
    }

    private LottoNumbers tryCreateManualLottoTicket() {
        try {
            return LottoNumbers.create(() -> InputView.inputLottoNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
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
