import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGame {
    public static void main(String[] args) {
        OutputView.printPaidPriceRequest();
        Money paidPrice = new Money(InputView.getInput());

        OutputView.printManualLottoCountRequest();
        Count manualLottoCount = new Count(InputView.getInput(), paidPrice.getCount());

        Lottos lottos = new Lottos();

        if (!manualLottoCount.zero) {
            OutputView.printManualLottoRequest();
            for (int i = 0; i < manualLottoCount.getCount(); i++) {
                lottos.addManualLotto(InputView.getInput());
            }
        }

        OutputView.printLottoCount(manualLottoCount.getCount(), manualLottoCount.remains);

        lottos.addAutoLottos(manualLottoCount.remains);

        OutputView.printLottos(lottos.getPurchasedLottosNumbers());

        OutputView.printWinningNumbersRequest();
        Lotto winningLotto = Lotto.getManualLotto(InputView.getInput());

        OutputView.printBonusNumberRequest();
        LottoNumber bonusNumber = new LottoNumber(winningLotto, InputView.getInput());

        WinningLottoWithBonus winningLottoWithBonus = new WinningLottoWithBonus(winningLotto, bonusNumber);

        TotalResult totalResult = lottos.getTotalResult(winningLottoWithBonus);
        List<String> totalResultMessage = totalResult.getTotalResultMessage();
        OutputView.printTotalResult(totalResultMessage);
        double profit = totalResult.getProfit(paidPrice.getMoney());
        OutputView.printProfit(totalResult.getProfitMessage(profit));
    }
}
