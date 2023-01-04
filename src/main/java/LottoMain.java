import common.constant.Constants;
import domain.BonusNumber;
import domain.Lottos;
import domain.TotalResult;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoMain {
    public static void main(String[] args) {

        OutputView.printPaidPriceRequest();
        int paidPrice = InputView.getPaidPrice();
        int count = paidPrice / Constants.PRICE;
        OutputView.printLottoCount(count);
        Lottos lottos = new Lottos(count);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        OutputView.printWinningNumbersRequest();
        WinningLotto winningLotto = new WinningLotto(InputView.getWinningLottoNumbers());
        OutputView.printBonusNumberRequest();
        BonusNumber bonusNumber = new BonusNumber(winningLotto, InputView.getBonusNumber());
        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        OutputView.printTotalResult(totalResult.getTotalResultMessage());
        OutputView.printProfit(totalResult.getProfitMessage(totalResult.getProfit(paidPrice)));
    }
}
