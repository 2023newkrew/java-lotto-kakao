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
        PurchaseAmount purchaseAmount = inputView.getPurchaseAmountInput();
        LottoCount manualLottoCount = inputView.getManualPurchaseCountInput();
        TotalLottoCount totalLottoCount = TotalLottoCount.of(manualLottoCount, purchaseAmount);

        Lottos lottos = simulateAndPrintLottoPurchase(totalLottoCount);

        WinningLotto winningLotto = inputView.getWinningLottoInput();

        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        outputView.printLottoResult(lottoResult, purchaseAmount);
    }

    private Lottos simulateAndPrintLottoPurchase(TotalLottoCount totalLottoCount) {
        Lottos manualLottos = inputView.getManualLottoNumbersInput(totalLottoCount.getManualLottoCount());
        Lottos autoLottos = lottoGenerator.generateLottos(totalLottoCount.getAutoLottoCount());
        Lottos totalLottos = Lottos.of(manualLottos, autoLottos);

        outputView.printLottoCount(totalLottoCount);
        outputView.printLottos(totalLottos);

        return totalLottos;
    }
}
