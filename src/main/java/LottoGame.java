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

//    public static WinningLottoWithBonus getWinningLotto() {
//        OutputView.printWinningNumbersRequest();
//        return new WinningLottoWithBonus(InputView.getWinningLottoNumbers());
//    }

//    public static LottoNumber getBonusNumber(WinningLottoWithBonus winningLottoWithBonus) {
//        OutputView.printBonusNumberRequest();
//        return new LottoNumber(winningLottoWithBonus, InputView.getBonusNumber());
//    }

    public static void main(String[] args) {
        Money paidPrice = getPaidPrice();
        Lottos lottos = getLottos(paidPrice.getCount());

        OutputView.printWinningNumbersRequest();
        Lotto winningLotto = Lotto.getManualLotto(InputView.getWinningLottoNumbers());

        OutputView.printBonusNumberRequest();
        LottoNumber bonusNumber = new LottoNumber(winningLotto, InputView.getBonusNumber());

        WinningLottoWithBonus winningLottoWithBonus = new WinningLottoWithBonus(winningLotto, bonusNumber);

        TotalResult totalResult = lottos.getTotalResult(winningLottoWithBonus);
        List<String> totalResultMessage = totalResult.getTotalResultMessage();
        OutputView.printTotalResult(totalResultMessage);
        double profit = totalResult.getProfit(paidPrice.getMoney());
        OutputView.printProfit(totalResult.getProfitMessage(profit));
    }
}
