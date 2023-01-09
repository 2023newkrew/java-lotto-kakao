import domain.*;
import view.InputView;
import view.OutputView;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!manualLottoCount.zero()) {
            OutputView.printManualLottoRequest();
        }
        Lottos manualLottos = new Lottos(
                Stream.generate(() -> InputView.getInput())
                        .limit(manualLottoCount.getCount())
                        .map(s -> LottoFactory.getLotto(new ManualLottoGenerator(s)))
                        .collect(Collectors.toList())
        );
        OutputView.printLottoCount(manualLottoCount.getCount(), manualLottoCount.getRemains());
        Lottos autoLottos = Lottos.getAutoLottos(manualLottoCount.getRemains());
        Lottos lottos = new Lottos(manualLottos, autoLottos);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        return lottos;
    }

    public static Lotto getWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return LottoFactory.getLotto(new ManualLottoGenerator(InputView.getInput()));
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
