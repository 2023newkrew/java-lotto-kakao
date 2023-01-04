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
        PurchaseAmount purchaseAmount = inputView.getPurchaseAmountInput();
        LottoCount totalLottoCount = LottoCount.of(purchaseAmount, PURCHASE_AMOUNT_UNIT_PRICE);
        LottoCount manuallyLottoCount = inputView.getManuallyLottoCountInput();
        Lottos manuallyLottos = inputView.getManuallyLottosInput(manuallyLottoCount);
        LottoCount automaticallyLottoCount = totalLottoCount.getRemainExceptFor(manuallyLottoCount);
        outputView.printLottoCount(manuallyLottoCount, automaticallyLottoCount);
        Lottos automaticallyLottos = lottoGenerator.getLottos(automaticallyLottoCount);
        Lottos totalLottos = manuallyLottos.addAll(automaticallyLottos);
        outputView.printLottos(totalLottos);
        WinningLotto winningLotto = inputView.getWinningLottoInput();
        outputView.printResult(winningLotto, totalLottos, purchaseAmount);
    }
}
