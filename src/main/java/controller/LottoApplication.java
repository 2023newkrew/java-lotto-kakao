package controller;

import domain.*;
import dto.LottoResult;
import dto.WinningLotto;
import utils.NumberParser;
import view.InputView;
import view.OutputView;

public class LottoApplication {

    public static WinningLotto makeWinningLotto() {
        String winningLottoInput = InputView.getWinningLottoInput();
        Lotto winningLotto = Lotto.ofNumbers(NumberParser.splitAndParse(winningLottoInput));
        String bonusNumberInput = InputView.getBonusNumberInput();
        LottoNumber bonusNumber = new LottoNumber(NumberParser.parse(bonusNumberInput));
        return new WinningLotto(winningLotto, bonusNumber);
    }

    public static void main(String[] args) {
        try {
            String moneyInput = InputView.getMoneyInput();
            Integer money = NumberParser.parse(moneyInput);

            String manualLottoAmountInput = InputView.getManualLottoAmountInput();
            Integer manualLottoAmount = NumberParser.parse(manualLottoAmountInput);
            Integer automaticLottoAmount = (money / LottoStore.COST) - manualLottoAmount;

            LottoBuyer lottoBuyer = new LottoBuyer(money);
            lottoBuyer.buyLottos(manualLottoAmount, automaticLottoAmount);
            OutputView.printPurchasedLottos(lottoBuyer.getLottos(), manualLottoAmount);

            LottoResult lottoResult = lottoBuyer.calculateResult(makeWinningLotto());
            OutputView.printLottoStatistics(lottoResult);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
