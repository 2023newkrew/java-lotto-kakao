package lotto.controller;

import lotto.model.*;
import lotto.model.lotto.Lotto;
import lotto.model.generator.RandomLottoGenerator;
import lotto.model.lotto.PurchaseResult;
import lotto.model.vo.LottoNumber;
import lotto.model.vo.Money;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoSimulator {

    private final LottoStore lottoStore = LottoStore.create(RandomLottoGenerator.getInstance());

    private final LottoInputView inputView = new LottoInputView();

    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        PurchaseResult purchaseResult = buyLotto(money);
        LottoAnalyzer lottoAnalyzer = createLottoAnalyzer();
        WinningStatistics winningStatistics = lottoAnalyzer.analyze(money, purchaseResult);
        outputView.printWinningStatistics(winningStatistics);
    }

    private PurchaseResult buyLotto(Money money) {
        PurchaseResult purchaseResult = lottoStore.buyLotto(money);
        outputView.printLottos(purchaseResult);

        return purchaseResult;
    }

    private LottoAnalyzer createLottoAnalyzer() {
        WinningNumber winningNumber = inputWinningNumbers();

        return LottoAnalyzer.create(winningNumber);
    }

    private WinningNumber inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();

        return WinningNumber.from(winningNumbers, bonus);
    }

}
