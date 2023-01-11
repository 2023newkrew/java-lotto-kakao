package lotto.controller;

import java.util.List;
import lotto.model.*;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoSimulator {

    private final LottoInputView inputView = new LottoInputView();
    private final LottoOutputView outputView = new LottoOutputView();

    public LottoSimulator() {
    }

    public void run() {
        Money money = inputView.inputMoney();

        List<Lotto> manualLottos = inputManualLottos();
        LottoMachine machine = createLottoMachine(money, manualLottos);
        outputView.printLottos(machine.getIssueLottos(), manualLottos.size());

        WinningNumbers winningNumbers = inputWinningNumbers();

        WinningStatistics winningStatistics = machine.judge(winningNumbers);
        outputView.printWinningStatistics(winningStatistics);
    }

    private List<Lotto> inputManualLottos() {
        int manualLottoCount = inputView.inputManualLottoCount();
        return inputView.inputManualLottos(manualLottoCount);
    }

    private static LottoMachine createLottoMachine(Money money, List<Lotto> manualLottos) {
        LottosGenerator lottosGenerator = new ManualAndAdditionalLottosGenerator(manualLottos, new RandomLottosGenerator());
        return LottoMachine.create(lottosGenerator, money);
    }

    private WinningNumbers inputWinningNumbers() {
        Lotto winningNumbers = inputView.inputWinningLotto();
        LottoNumber bonus = inputView.inputBonusNumber();
        return new WinningNumbers(winningNumbers, bonus);
    }

}
