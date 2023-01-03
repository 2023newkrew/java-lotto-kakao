package javalotto.controller;

import javalotto.domain.*;
import javalotto.view.InputView;
import javalotto.view.OutputView;

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

        LottoCount lottoCount = LottoCount.of(purchaseAmount);
        outputView.printLottoCount(lottoCount);

        Lottos lottos = lottoGenerator.generateLottos(lottoCount);
        outputView.printLottos(lottos);

        WinningLotto winningLotto = inputView.getWinningLottoInput();

        LottoResult lottoResult = lottos.getLottoResult(winningLotto);
        outputView.printLottoResult(lottoResult, purchaseAmount);

    }
}
