package lotto.controller;

import lotto.model.*;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.List;

public class LottoSimulator {

    private final LottosGenerator lottosGenerator = new RandomLottosGenerator();
    private final LottoInputView inputView = new LottoInputView();
    private final LottoOutputView outputView = new LottoOutputView();

    public void run() {
        Money money = inputView.inputMoney();
        List<Lotto> lottos = lottosGenerator.generate(money.getLottoCount());
        outputView.printLottos(lottos);
        WinningNumbers winningNumbers = inputWinningNumbers();
        LottoCompany lottoCompany = new LottoCompany(winningNumbers);
        WinningStatistics winningStatistics = lottoCompany.judge(lottos, money);
        outputView.printWinningStatistics(winningStatistics);
    }

    private WinningNumbers inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return new WinningNumbers(winningNumbers, bonus);
    }

}
