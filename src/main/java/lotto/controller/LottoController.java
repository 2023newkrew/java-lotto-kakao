package lotto.controller;

import lotto.model.LottoService;
import lotto.model.PriceResult;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        // 로또 생성
        long purchaseCost = InputView.inputPurchaseCost();
        LottoService lottoService = new LottoService(purchaseCost);
        lottoService.createLottos();

        // 수동 입력
        int manualLottoCount = InputView.inputManualLottoCount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualLottoCount);
        lottoService.setManualLottos(manualLottoNumbers);

        OutputView.printLottoCount(manualLottoCount, lottoService.getLottos().size() - manualLottoCount);

        OutputView.printLottos(lottoService.getLottos());

        // 로또 당첨 번호 매칭
        List<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusBall();
        lottoService.createWinningNumbers(winningNumbers, bonusNumber);

        // 결과 출력
        PriceResult result = lottoService.getResult();
        OutputView.printResult(result);
        OutputView.printEarningRate(lottoService.getEarningsRate());
    }
}
