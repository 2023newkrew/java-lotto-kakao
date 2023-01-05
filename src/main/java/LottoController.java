import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {


    public void run() {
        Money money = payMoney();
        Lottos lottos = purChaseLotto(money.getLottoCount());
        WinningLotto winningLotto = inputWinningLotto();
        BonusNumber bonusNumber = inputBonusNumber(winningLotto);
        showTotalResult(money, lottos, winningLotto, bonusNumber);
    }


    public Money payMoney() {
        OutputView.printPaidPriceRequest();
        return new Money(InputView.getPaidPrice());
    }

    public Lottos purChaseLotto(int lottoCount) {
        OutputView.printLottoCount(lottoCount);
        Lottos lottos = new Lottos(lottoCount);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
        return lottos;
    }

    public WinningLotto inputWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return new WinningLotto(InputView.getWinningLottoNumbers());
    }

    public BonusNumber inputBonusNumber(WinningLotto winningLotto) {
        OutputView.printBonusNumberRequest();
        return new BonusNumber(winningLotto, InputView.getBonusNumber());
    }

    public void showTotalResult(Money money, Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        OutputView.printTotalResult(totalResult.getTotalResultMessage());
        OutputView.printProfit(totalResult.getProfitMessage(totalResult.getProfit(money.getPaidMoney())));
    }
}
