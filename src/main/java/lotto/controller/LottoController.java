package lotto.controller;

import lotto.model.LottoService;
import lotto.model.PriceResult;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        // 로또 생성을 위한 사용자 입력
        long purchaseCost = InputView.inputPurchaseCost();
        int manualLottoCount = InputView.inputManualLottoCount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualLottoCount);

        // 로또 생성
        LottoService lottoService = new LottoService(purchaseCost, manualLottoNumbers);

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
