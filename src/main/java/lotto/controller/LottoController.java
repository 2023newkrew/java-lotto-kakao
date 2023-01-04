package lotto.controller;

import lotto.model.LottoResult;
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

    public void startLottoGame() {
        String inputPurchaseAmount = lottoInputTemplate.inputPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(inputPurchaseAmount);
        lottoService.purchaseLotto(purchaseAmount);
        lottoOutputTemplate.printLottoTickets(lottoService.getLottoTickets());

        String inputWinningNumbers = lottoInputTemplate.inputLottoWinningNumbers();
        String inputBonusBall = lottoInputTemplate.inputBonusBall();
        LottoResult lottoResult = lottoService.getLottoResult(inputWinningNumbers, inputBonusBall);
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(purchaseAmount, lottoResult));
    }
}
