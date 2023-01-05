package javalotto.controller;

import javalotto.domain.*;
import javalotto.view.InputView;
import javalotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoShop lottoShop;

    private LottoController(InputView inputView, OutputView outputView, LottoShop lottoShop) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoShop = lottoShop;
    }

    public static LottoController of(InputView inputView, OutputView outputView, LottoShop lottoShop) {
        return new LottoController(inputView, outputView, lottoShop);
    }

    public void simulate() {
        LottoBuyer lottoBuyer = getLottoBuyerInput();

        Lottos lottos = simulateLottoPurchase(lottoBuyer);
        outputView.printLottos(lottoBuyer, lottos);

        WinningLotto winningLotto = getWinningLottoInput();
        LottoResult lottoResult = lottoBuyer.getLottoResult(winningLotto);
        outputView.printLottoResult(lottoBuyer, lottoResult);
    }

    private LottoBuyer getLottoBuyerInput() {
        PurchaseAmount purchaseAmount = PurchaseAmount.from(inputView.getPurchaseAmountInput());
        LottoCount manualLottoCount = LottoCount.from(inputView.getManualPurchaseCountInput());
        TotalLottoCount totalLottoCount = TotalLottoCount.of(manualLottoCount, purchaseAmount);

        return LottoBuyer.of(purchaseAmount, totalLottoCount);
    }

    private Lottos simulateLottoPurchase(LottoBuyer lottoBuyer) {
        List<List<Integer>> manualLottoNumbers = inputView.getManualLottoNumbersInput(lottoBuyer.getManualLottoCount());

        return lottoBuyer.purchaseLottos(lottoShop, manualLottoNumbers);
    }

    private WinningLotto getWinningLottoInput() {
        List<Integer> winningNumbers = inputView.getWinningNumbersInput();
        int bonusNumber = inputView.getBonusNumberInput();

        return WinningLotto.of(Lotto.from(winningNumbers), bonusNumber);
    }
}
