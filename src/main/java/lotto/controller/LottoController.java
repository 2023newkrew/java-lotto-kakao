package lotto.controller;

import lotto.model.LottoRank;
import lotto.service.LottoService;
import lotto.view.LottoInputTemplate;
import lotto.view.LottoOutputTemplate;

import java.util.List;
import java.util.Map;

public class LottoController {
    private final LottoInputTemplate lottoInputTemplate;
    private final LottoOutputTemplate lottoOutputTemplate;
    private final LottoService lottoService;

    public LottoController(){
        lottoInputTemplate = new LottoInputTemplate();
        lottoOutputTemplate = new LottoOutputTemplate();
        lottoService = new LottoService();
    }

    public void startLottoGame() {
        Integer inputPurchaseAmount = lottoInputTemplate.inputPurchaseAmount();
        lottoService.purchaseLotto(inputPurchaseAmount);
        lottoOutputTemplate.printPurchaseHistory(lottoService.getLottoTickets());

        List<Integer> inputWinningNumber = lottoInputTemplate.inputLottoNumber();
        Integer inputBonusBall = lottoInputTemplate.inputBonusBall();
        Map<LottoRank, Integer> lottoResult = lottoService.getLottoResult(inputWinningNumber, inputBonusBall);
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(inputPurchaseAmount, lottoResult));
    }
}
