import common.constant.Constants;
import domain.LottoNumber;
import domain.Lottos;
import domain.TotalResult;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGame {

    public static int getPaidPrice() {
        OutputView.printPaidPriceRequest();
        int paidPrice = InputView.getPaidPrice();
        OutputView.printLottoCount(paidPrice / Constants.PRICE);
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
        int paidPrice = getPaidPrice();
        int count = paidPrice / Constants.PRICE;
        Lottos lottos = getLottos(count);
        WinningLotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber(winningLotto);

        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        List<String> totalResultMessage = totalResult.getTotalResultMessage();
        OutputView.printTotalResult(totalResultMessage);
        double profit = totalResult.getProfit(paidPrice);
        OutputView.printProfit(totalResult.getProfitMessage(profit));
    }
}
