package controller;

import domain.Lotto;
import domain.LottoBuyer;
import domain.LottoNumber;
import domain.LottoStore;
import dto.LottoResult;
import dto.PurchasedLotto;
import dto.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;


public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoStore lottoStore = new LottoStore();

        try {
            Integer money = inputView.getMoneyInput();

            Integer amount = inputView.getManualAmountInput();

            List<Lotto> manualLottos = inputView.getManualLottosInput(amount)
                    .stream()
                    .map(Lotto::ofManual)
                    .collect(Collectors.toList());

            LottoBuyer lottoBuyer = new LottoBuyer(money, lottoStore);
            lottoBuyer.buyManual(manualLottos);
            lottoBuyer.buyAuto();
            outputView.printPurchasedLottos(PurchasedLotto.of(lottoBuyer));

            Lotto winningLotto = Lotto.ofManual(inputView.getWinningLottoInput());
            LottoNumber bonusNumber = new LottoNumber(inputView.getBonusNumberInput());
            inputView.close();

            LottoResult lottoResult = lottoBuyer.calculateResult(new WinningLotto(winningLotto, bonusNumber));
            outputView.printLottoStatistics(lottoResult);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
