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
        PurchaseAmount purchaseAmount = new PurchaseAmount(lottoInputTemplate.inputPurchaseAmount());
        lottoService.purchaseLotto(purchaseAmount);
        lottoOutputTemplate.printLottoTickets(lottoService.getLottoTickets());

        LottoResult lottoResult = lottoService.getLottoResult(lottoInputTemplate.inputLottoWinningNumbers(), lottoInputTemplate.inputBonusBall());
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(purchaseAmount, lottoResult));
    }
}
