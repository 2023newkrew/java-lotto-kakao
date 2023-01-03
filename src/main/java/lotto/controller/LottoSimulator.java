package lotto.controller;

import lotto.model.LottoStore;
import lotto.model.PurchaseResult;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.WinningNumber;
import lotto.model.generator.LottoGenerator;
import lotto.model.generator.RandomLottoGenerator;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.LottoNumber;
import lotto.model.vo.Money;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.math.BigDecimal;
import java.util.List;

public class LottoSimulator {

    private final LottoStore lottoStore = LottoStore.create(RandomLottoGenerator.getInstance());

    private final LottoInputView inputView = new LottoInputView();

    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        PurchaseResult purchaseResult = lottoStore.buyLotto(money);
        LottoBundle lottoBundle = purchaseResult.getLottoBundle();
        outputView.printLottos(lottoBundle);
        WinningNumber winningNumber = inputWinningNumbers();
        PrizeMap prizeMap = winningNumber.judge(lottoBundle);
        BigDecimal profitRate = prizeMap.getProfitRate(money, purchaseResult.getTotalPrice());
        outputView.printWinningStatistics(prizeMap, profitRate);
    }

    private WinningNumber inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return WinningNumber.from(winningNumbers, bonus);
    }

}
