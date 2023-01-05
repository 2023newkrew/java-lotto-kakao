import common.constant.Constants;
import domain.*;
import view.InputView;
import view.OutputView;

public class LottoMain {
    public static void main(String[] args) {

        OutputView.printPaidPriceRequest();
        Money money = new Money(InputView.getPaidPrice());
        int count = money.getLottoCount();

        OutputView.printLottoCount(count);
        Lottos lottos = new Lottos(count);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());

        OutputView.printWinningNumbersRequest();
        WinningLotto winningLotto = new WinningLotto(InputView.getWinningLottoNumbers());
        OutputView.printBonusNumberRequest();
        BonusNumber bonusNumber = new BonusNumber(winningLotto, InputView.getBonusNumber());

        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        OutputView.printTotalResult(totalResult.getTotalResultMessage());
        OutputView.printProfit(totalResult.getProfitMessage(totalResult.getProfit(money.getPaidMoney())));
    }
}
