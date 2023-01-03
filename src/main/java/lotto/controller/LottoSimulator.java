package lotto.controller;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.WinningNumber;
import lotto.model.generator.LottosGenerator;
import lotto.model.generator.RandomLottosGenerator;
import lotto.model.prize.PrizeMap;
import lotto.model.vo.LottoNumber;
import lotto.model.vo.Money;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.math.BigDecimal;
import java.util.List;

public class LottoSimulator {

    private static final Money LOTTO_PRICE = Money.valueOf(1000L);

    private final LottosGenerator lottosGenerator = new RandomLottosGenerator();
    private final LottoInputView inputView = new LottoInputView();
    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        long lottoCount = money.getPurchasableCount(LOTTO_PRICE);
        Money totalPrice = Money.valueOf(money.longValue() - lottoCount * LOTTO_PRICE.longValue());
        List<Lotto> lottos = lottosGenerator.generate(lottoCount);
        LottoBundle lottoBundle = LottoBundle.from(lottos);
        outputView.printLottos(lottos);
        WinningNumber winningNumber = inputWinningNumbers();
        PrizeMap prizeMap = winningNumber.judge(lottoBundle);
        BigDecimal profitRate = prizeMap.getProfitRate(money, totalPrice);
        outputView.printWinningStatistics(prizeMap, profitRate);
    }

    private WinningNumber inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return WinningNumber.from(winningNumbers, bonus);
    }

}
