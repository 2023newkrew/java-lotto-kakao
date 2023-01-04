package javalotto.controller;

import javalotto.domain.*;
import javalotto.view.InputView;
import javalotto.view.OutputView;

import static javalotto.domain.PurchaseAmount.PURCHASE_AMOUNT_UNIT_PRICE;

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

    public void play() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        TotalLottoCount totalLottoCount = getTotalLottoCount(purchaseAmount);
        Lottos totalLottos = getTotalLottos(totalLottoCount);
        outputView.printLottoCount(totalLottoCount);
        outputView.printLottos(totalLottos);
        WinningLotto winningLotto = getWinningLotto();
        outputView.printResult(winningLotto, totalLottos, purchaseAmount);
    }

    private PurchaseAmount getPurchaseAmount() {
        return inputView.getPurchaseAmountInput();
    }

    private TotalLottoCount getTotalLottoCount(PurchaseAmount purchaseAmount) {
        LottoCount totalLottoCount = LottoCount.of(purchaseAmount, PURCHASE_AMOUNT_UNIT_PRICE);
        LottoCount manuallyLottoCount = inputView.getManuallyLottoCountInput();
        return TotalLottoCount.of(totalLottoCount, manuallyLottoCount);
    }

    private Lottos getTotalLottos(TotalLottoCount totalLottoCount) {
        Lottos manuallyLottos = inputView.getManuallyLottosInput(totalLottoCount.getManuallyLottoCount());
        Lottos automaticallyLottos = lottoGenerator.getLottos(totalLottoCount.getAutomaticallyLottoCount());
        return manuallyLottos.addAll(automaticallyLottos);
    }

    private WinningLotto getWinningLotto() {
        return inputView.getWinningLottoInput();
    }
}
