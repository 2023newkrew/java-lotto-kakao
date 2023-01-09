import domain.*;
import view.InputView;
import view.OutputView;

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
        if (!manualLottoCount.zero()) {
            OutputView.printManualLottoRequest();
        }
        for (int i = 0; i < manualLottoCount.getCount(); i++) {
            lottos.addManualLotto(Lotto.getLotto(new ManualLottoGenerator(InputView.getInput())));
        }
        OutputView.printLottoCount(manualLottoCount.getCount(), manualLottoCount.getRemains());
        lottos.addAutoLottos(manualLottoCount.getRemains());
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        return lottos;
    }

    public static Lotto getWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return Lotto.getLotto(new ManualLottoGenerator(InputView.getInput()));
    }

    public static LottoNumber getBonusNumber(Lotto winningLotto) {
        OutputView.printBonusNumberRequest();
        return new LottoNumber(winningLotto, InputView.getInput());
    }

    public static void main(String[] args) {
        Money paidPrice = getPaidPrice();
        Count manualLottoCount = getManualLottoCount(paidPrice);
        Lottos lottos = getLottos(manualLottoCount);
        Lotto lotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber(lotto);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        TotalResult totalResult = lottos.getTotalResult(winningLotto);
        OutputView.printTotalResult(totalResult);
        OutputView.printProfit(totalResult.getProfit(paidPrice.getMoney()));
    }
}
