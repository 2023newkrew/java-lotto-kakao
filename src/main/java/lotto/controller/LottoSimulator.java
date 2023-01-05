package lotto.controller;

import lotto.model.*;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoSimulator {

    private final LottosGenerator lottosGenerator;
    private final LottoInputView inputView = new LottoInputView();
    private final LottoOutputView outputView = new LottoOutputView();

    public LottoSimulator(LottosGenerator lottosGenerator) {
        this.lottosGenerator = lottosGenerator;
    }

    public void run() {
        Money money = inputView.inputMoney();

        LottoMachine machine = LottoMachine.create(lottosGenerator, money);
        outputView.printLottos(machine.getIssueLottos());

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStatistics winningStatistics = machine.judge(winningNumbers);
        outputView.printWinningStatistics(winningStatistics);
    }

    private WinningNumbers inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return new WinningNumbers(winningNumbers, bonus);
    }

}
