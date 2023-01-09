package lotto.controller;

import java.util.List;
import lotto.model.LottoCount;
import lotto.model.LottoResult;
import lotto.model.LottoWinningNumber;
import lotto.model.PurchaseAmount;
import lotto.service.LottoService;
import lotto.view.LottoInputTemplate;
import lotto.view.LottoOutputTemplate;

public class LottoController {
    private final LottoInputTemplate lottoInputTemplate;
    private final LottoOutputTemplate lottoOutputTemplate;
    private final LottoService lottoService;

    public LottoController() {
        lottoInputTemplate = new LottoInputTemplate();
        lottoOutputTemplate = new LottoOutputTemplate();
        lottoService = new LottoService();
    }

    private PurchaseAmount inputPurchaseAmount() {
        try {
            String inputPurchaseAmountString = lottoInputTemplate.inputPurchaseAmount();
            return new PurchaseAmount(inputPurchaseAmountString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    private LottoCount inputManualLottoCount(Integer totalLottoCount) {
        try {
            String manualLottoCount = lottoInputTemplate.inputManualLottoCount();
            return new LottoCount(totalLottoCount, manualLottoCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputManualLottoCount(totalLottoCount);
        }
    }

    private void inputManualLottos(LottoCount lottoCount) {
        try {
            List<String> manualLottos = lottoInputTemplate.inputManualLottos(lottoCount.getManualLottoCount());
            lottoService.purchaseLotto(lottoCount, manualLottos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputManualLottos(lottoCount);
        }
    }

    private void outputLottoTickets(LottoCount lottoCount) {
        lottoOutputTemplate.printLottoTickets(lottoCount, lottoService.getLottoTickets());
    }

    private LottoWinningNumber inputLottoWinningNumber() {
        try {
            String inputLottoWinningNumbers = lottoInputTemplate.inputLottoWinningNumbers();
            String inputBonusBall = lottoInputTemplate.inputBonusBall();
            return new LottoWinningNumber(inputLottoWinningNumbers, inputBonusBall);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputLottoWinningNumber();
        }
    }

    private void outputLottoResult(PurchaseAmount purchaseAmount, LottoWinningNumber lottoWinningNumber) {
        LottoResult lottoResult = lottoService.getLottoResult(lottoWinningNumber);
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(purchaseAmount, lottoResult));
    }

    public void startLottoGame() {
        PurchaseAmount purchaseAmount = inputPurchaseAmount();
        Integer totalLottoCount = lottoService.getTotalLottoCount(purchaseAmount);
        LottoCount lottoCount = inputManualLottoCount(totalLottoCount);
        inputManualLottos(lottoCount);
        outputLottoTickets(lottoCount);
        LottoWinningNumber lottoWinningNumber = inputLottoWinningNumber();
        outputLottoResult(purchaseAmount, lottoWinningNumber);
    }
}
