package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        try {
            int amount = getPurchaseAmount();
            UserLottos userLottos = new UserLottos(LottoSeller.generateRandomLottos(amount));
            outputView.printUserLottos(userLottos.getLottoNumbers());
            List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
            LottoNumber bonusNumber = LottoNumber.from(inputView.getBonusBallInput());

            List<LottoNumber> answerLottoNumbers = lottoNumbers.stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            WinningLotto winningLotto = new WinningLotto(new LottoNumbers(answerLottoNumbers), bonusNumber);
            outputView.printResult(winningLotto.getPrizeCountMap(userLottos.getLottoNumbers()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play();
        }
    }

    private int getPurchaseAmount() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        int amount = LottoSeller.getLottoAmount(money);
        outputView.printPurchaseResult(amount);
        return amount;
    }
}
