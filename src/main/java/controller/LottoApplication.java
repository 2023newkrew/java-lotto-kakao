package controller;

import domain.Lotto;
import domain.LottoBuyer;
import domain.LottoNumber;
import domain.Store;
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

            LottoBuyer lottoBuyer = new LottoBuyer(money, new Store());
            outputView.printPurchasedLottos(lottoBuyer.getLottos());

            String winningLottoInput = inputView.getWinningLottoInput();
            Lotto winningLotto = Lotto.ofNumbers(NumberParser.splitAndParse(winningLottoInput));
            String bonusNumberInput = inputView.getBonusNumberInput();
            LottoNumber bonusNumber = new LottoNumber(NumberParser.parse(bonusNumberInput));

            LottoResult lottoResult = lottoBuyer.calculateResult(new WinningLotto(winningLotto, bonusNumber));
            outputView.printLottoStatistics(lottoResult);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
