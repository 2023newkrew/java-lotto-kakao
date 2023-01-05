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
        Integer inputManualNumberOfLotto = lottoInputTemplate.inputNumberOfManualLottoTicket(inputPurchaseAmount);
        List<List<Integer>> inputManualLottoNumbers = lottoInputTemplate.inputManualLottoNumbers(inputManualNumberOfLotto);

        lottoService.purchaseRandomLotto(inputPurchaseAmount, inputManualNumberOfLotto);
        lottoService.purchaseManualLotto(inputManualLottoNumbers);
        lottoOutputTemplate.printPurchaseHistory(lottoService.getLottoTickets());

        List<Integer> inputWinningNumber = lottoInputTemplate.inputWinningNumbers();
        Integer inputBonusBall = lottoInputTemplate.inputBonusBall();
        Map<LottoRank, Integer> lottoResult = lottoService.getLottoResult(inputWinningNumber, inputBonusBall);
        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(inputPurchaseAmount, lottoResult));
    }
}
