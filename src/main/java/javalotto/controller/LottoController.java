package javalotto.controller;

import javalotto.domain.*;
import javalotto.view.InputView;
import javalotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    private LottoController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public static LottoController of(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        return new LottoController(inputView, outputView, lottoGenerator);
    }

    public void simulate() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(inputView.getPurchaseAmountInput());
        LottoCount manualLottoCount = LottoCount.from(inputView.getManualPurchaseCountInput());
        TotalLottoCount totalLottoCount = TotalLottoCount.of(manualLottoCount, purchaseAmount);

        Lottos lottos = simulateLottoPurchase(totalLottoCount);

        outputView.printLottoCount(totalLottoCount);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = getWinningLottoInput();

        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        outputView.printLottoResult(lottoResult, purchaseAmount);
    }

    private Lottos simulateLottoPurchase(TotalLottoCount totalLottoCount) {
        List<List<Integer>> manualLottoNumbers = inputView.getManualLottoNumbersInput(totalLottoCount.getManualLottoCount());
        Lottos manualLottos = Lottos.fromNumbers(manualLottoNumbers);

        Lottos autoLottos = lottoGenerator.generateLottos(totalLottoCount.getAutoLottoCount());
        Lottos totalLottos = Lottos.of(manualLottos, autoLottos);

        return totalLottos;
    }

    private WinningLotto getWinningLottoInput() {
        List<Integer> winningNumbers = inputView.getWinningNumbersInput();
        int bonusNumber = inputView.getBonusNumberInput();

        return WinningLotto.of(Lotto.from(winningNumbers), bonusNumber);
    }
}
