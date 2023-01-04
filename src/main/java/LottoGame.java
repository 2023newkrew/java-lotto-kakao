import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGame {

    public static Money getPaidPrice() {
        OutputView.printPaidPriceRequest();
        return new Money(InputView.getInput());
    }

    public static Count getManualLottoCount(Money paidPrice) {
        OutputView.printManualLottoCountRequest();
        return new Count(InputView.getInput(), paidPrice.getCount());
    }

    public static Lottos getLottos(Count manualLottoCount) {
        Lottos lottos = new Lottos();
        if (!manualLottoCount.zero) {
            OutputView.printManualLottoRequest();
        }
        for (int i = 0; i < manualLottoCount.getCount(); i++) {
            lottos.addManualLotto(InputView.getInput());
        }
        OutputView.printLottoCount(manualLottoCount.getCount(), manualLottoCount.remains);
        lottos.addAutoLottos(manualLottoCount.remains);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        return lottos;
    }

    public static Lotto getWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return Lotto.getManualLotto(InputView.getInput());
    }

    public static LottoNumber getBonusNumber(Lotto winningLotto) {
        OutputView.printBonusNumberRequest();
        return new LottoNumber(winningLotto, InputView.getInput());
    }

    public static void main(String[] args) {
        Money paidPrice = getPaidPrice();
        Count manualLottoCount = getManualLottoCount(paidPrice);
        Lottos lottos = getLottos(manualLottoCount);
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber(winningLotto);
        WinningLottoWithBonus winningLottoWithBonus = new WinningLottoWithBonus(winningLotto, bonusNumber);
        TotalResult totalResult = lottos.getTotalResult(winningLottoWithBonus);
        OutputView.printTotalResult(totalResult.getTotalResultMessage());
        OutputView.printProfit(totalResult.getProfitMessage(paidPrice.getMoney()));
    }
}
