package controller;

import domain.Lotto;
import domain.LottoBuyer;
import domain.LottoNumber;
import domain.LottoStore;
import dto.LottoResult;
import dto.WinningLotto;
import utils.NumberParser;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        try {
            String moneyInput = inputView.getMoneyInput();
            Integer money = NumberParser.parse(moneyInput);

            LottoBuyer lottoBuyer = new LottoBuyer(money);
            outputView.printPurchasedLottos(lottoBuyer.buyFrom(new LottoStore()));

            String winningLottoInput = inputView.getWinningLottoInput();
            Lotto winningLotto = Lotto.ofManual(NumberParser.splitAndParse(winningLottoInput));

            String bonusNumberInput = inputView.getBonusNumberInput();
            LottoNumber bonusNumber = new LottoNumber(NumberParser.parse(bonusNumberInput));
            inputView.close();

            LottoResult lottoResult = lottoBuyer.calculateResult(new WinningLotto(winningLotto, bonusNumber));
            outputView.printLottoStatistics(lottoResult);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
