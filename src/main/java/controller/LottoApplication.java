package controller;

import domain.Lotto;
import domain.LottoBuyer;
import domain.LottoNumber;
import domain.LottoStore;
import dto.LottoResult;
import dto.WinningLotto;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;


public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        try {
            // 구입금액 입력
            Integer money = inputView.getMoneyInput();

            // 수동 구매 수 입력
            Integer amount = inputView.getManualAmountInput();

            List<Lotto> manualLottos = inputView.getManualLottosInput(amount)
                    .stream()
                    .map(Lotto::ofManual)
                    .collect(Collectors.toList());

            // 수동 구매 (입력) + 자동 구매 (남은 금액) 로또 구매
            LottoBuyer lottoBuyer = new LottoBuyer(money);
            outputView.printPurchasedLottos(lottoBuyer.buyFrom(new LottoStore(), manualLottos));

            // 당첨번호 입력
            Lotto winningLotto = Lotto.ofManual(inputView.getWinningLottoInput());

            // 보너스 번호 입력
            LottoNumber bonusNumber = new LottoNumber(inputView.getBonusNumberInput());
            inputView.close();

            // 로또 당첨 결과 출력
            LottoResult lottoResult = lottoBuyer.calculateResult(new WinningLotto(winningLotto, bonusNumber));
            outputView.printLottoStatistics(lottoResult);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
