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
        Integer purchaseAmount = lottoInputTemplate.inputPurchaseAmount();
        Integer numberOfManualLottoTicket = lottoInputTemplate.inputNumberOfManualLottoTicket(purchaseAmount);
        List<List<Integer>> inputManualLottoNumbers = lottoInputTemplate.inputManualLottoNumbers(numberOfManualLottoTicket);

        lottoService.purchaseRandomLotto(purchaseAmount, numberOfManualLottoTicket);
        lottoService.purchaseManualLotto(inputManualLottoNumbers);
        lottoOutputTemplate.printPurchaseHistory(lottoService.getLottoTickets());

        List<Integer> winningNumbers = lottoInputTemplate.inputWinningNumbers();
        Integer bonusBall = lottoInputTemplate.inputBonusBall();
        Map<LottoRank, Integer> lottoResult = lottoService.getLottoResult(winningNumbers, bonusBall);

        lottoOutputTemplate.printLottoResult(lottoResult);
        lottoOutputTemplate.printRateOfReturn(lottoService.getRateOfReturn(purchaseAmount, lottoResult));
    }

}
