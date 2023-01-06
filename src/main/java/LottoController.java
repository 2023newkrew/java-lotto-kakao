import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final Lottos lottos = new Lottos();

    public void run() {
        Money money = payMoney();
        purchaseLotto(money.getLottoCount());
        WinningLotto winningLotto = inputWinningLotto();
        BonusNumber bonusNumber = inputBonusNumber(winningLotto);
        showTotalResult(money, lottos, winningLotto, bonusNumber);
    }

    private Money payMoney() {
        OutputView.printPaidPriceRequest();
        return new Money(InputView.getPaidPrice());
    }

    private void purchaseLotto(int totalLottoCount) {
        OutputView.printManualLottoCountRequest();
        LottoCount lottoCount = new LottoCount(totalLottoCount, InputView.getManualLottoCount());
        purchaseLottoManually(lottoCount.getManualLottoCount());
        purchaseLottoAutomatically(lottoCount.getAutomaticLottoCount());
        OutputView.printLottoCount(lottoCount.getManualLottoCount(), lottoCount.getAutomaticLottoCount());
    }

    private void purchaseLottoManually(int lottoCount) {
        OutputView.printManualLottosNumbersRequest();
        for (int i = 0; i < lottoCount; i++) {
            String lottoNumbers = InputView.getLottoNumbers();
            lottos.addLottoManually(new Lotto(lottoNumbers));
        }
    }

    private void purchaseLottoAutomatically(int lottoCount) {
        lottos.addLottoAutomatically(lottoCount);
        OutputView.printLottos(lottos.getPurchasedLottosNumbers());
    }


    private WinningLotto inputWinningLotto() {
        OutputView.printWinningNumbersRequest();
        return new WinningLotto(InputView.getWinningLottoNumbers());
    }

    private BonusNumber inputBonusNumber(WinningLotto winningLotto) {
        OutputView.printBonusNumberRequest();
        return new BonusNumber(winningLotto, InputView.getBonusNumber());
    }

    private void showTotalResult(Money money, Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        TotalResult totalResult = lottos.getTotalResult(winningLotto, bonusNumber);
        OutputView.printTotalResult(totalResult.getTotalResultMessage());
        OutputView.printProfit(totalResult.getProfitMessage(totalResult.getProfit(money.getPaidMoney())));
    }
}
