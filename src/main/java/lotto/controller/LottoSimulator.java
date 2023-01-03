package lotto.controller;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoBundle;
import lotto.model.LottoCompany;
import lotto.model.WinningNumbers;
import lotto.model.generator.LottosGenerator;
import lotto.model.generator.RandomLottosGenerator;
import lotto.model.vo.LottoNumber;
import lotto.model.vo.Money;
import lotto.model.WinningStatistics;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.List;

public class LottoSimulator {

    private static final Money LOTTO_PRICE = Money.valueOf(1000L);

    private final LottosGenerator lottosGenerator = new RandomLottosGenerator();
    private final LottoInputView inputView = new LottoInputView();
    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        long lottoCount = money.getPurchasableCount(LOTTO_PRICE);
        List<Lotto> lottos = lottosGenerator.generate(lottoCount);
        outputView.printLottos(lottos);
        WinningNumbers winningNumbers = inputWinningNumbers();
        LottoCompany lottoCompany = new LottoCompany(winningNumbers);
        WinningStatistics winningStatistics = lottoCompany.judge(LottoBundle.from(lottos), money);
        outputView.printWinningStatistics(winningStatistics);
    }

    private WinningNumbers inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return new WinningNumbers(winningNumbers, bonus);
    }

}
