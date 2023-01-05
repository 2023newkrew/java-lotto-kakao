package lotto.controller;

import java.util.List;
import lotto.model.LottoCount;
import lotto.model.LottoResult;
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

    private void inputPurchaseAmountController() {
        try {
            String inputPurchaseAmount = lottoInputTemplate.inputPurchaseAmount();
            lottoService.setPurchaseAmount(inputPurchaseAmount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputPurchaseAmountController();
        }
    }

    private void inputManualLottoCountController() {
        try {
            String manualLottoCount = lottoInputTemplate.inputManualLottoCount();
            lottoService.setLottoCount(manualLottoCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputManualLottoCountController();
        }
    }

    private void inputManualLottosController() {
        try {
            LottoCount lottoCount = lottoService.getLottoCount();
            List<String> manualLottos = lottoInputTemplate.inputManualLottos(lottoCount.getManualLottoCount());
            lottoService.purchaseLotto(manualLottos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputManualLottosController();
        }
    }

    private void outputLottoTicketsController() {
        lottoOutputTemplate.printLottoTickets(lottoService.getLottoCount(), lottoService.getLottoTickets());
    }

    private void inputLottoWinningNumberController() {
        try {
            String inputLottoWinningNumbers = lottoInputTemplate.inputLottoWinningNumbers();
            String inputBonusBall = lottoInputTemplate.inputBonusBall();
            lottoService.setLottoWinningNumber(inputLottoWinningNumbers, inputBonusBall);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            inputLottoWinningNumberController();
        }
    }

    private void outputLottoResultController() {
        LottoResult lottoResult = lottoService.getLottoResult();
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(lottoResult));
    }

    public void startLottoGame() {
        inputPurchaseAmountController();
        inputManualLottoCountController();
        inputManualLottosController();
        outputLottoTicketsController();
        inputLottoWinningNumberController();
        outputLottoResultController();
    }
}
