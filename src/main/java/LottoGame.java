import common.constant.Constants;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGame {

    public static Money getPaidPrice() {
        OutputView.printPaidPriceRequest();
        Money paidPrice = new Money(InputView.getPaidPrice());
        OutputView.printLottoCount(paidPrice.getCount());
        return paidPrice;
    }

    public static Lottos getLottos(int count) {
        Lottos lottos = new Lottos(count);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        return lottos;
    }

    public static WinningLotto getWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return new WinningLotto(InputView.getWinningLottoNumbers());
    }

    public static LottoNumber getBonusNumber(WinningLotto winningLotto) {
        OutputView.printBonusNumberRequest();
        return new LottoNumber(winningLotto, InputView.getBonusNumber());
    }

    public static void main(String[] args) {
        Money paidPrice = getPaidPrice();
        Lottos lottos = getLottos(paidPrice.getCount());
        WinningLotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber(winningLotto);

        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        List<String> totalResultMessage = totalResult.getTotalResultMessage();
        OutputView.printTotalResult(totalResultMessage);
        double profit = totalResult.getProfit(paidPrice.getMoney());
        OutputView.printProfit(totalResult.getProfitMessage(profit));
    }
}
