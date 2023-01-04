package lotto.controller;

import lotto.domain.*;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        int amount = getPurchaseAmount();
        UserLottos userLottos = new UserLottos(RandomLottoGenerator.generateLottos(amount));
        outputView.printUserLottos(userLottos.getLottoNumbers());
        List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
        LottoNumber bonusNumber = new LottoNumber(inputView.getBonusBallInput());

        List<LottoNumber> answerLottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        WinningLotto winningLotto = new WinningLotto(new LottoNumbers(answerLottoNumbers), bonusNumber);
        outputView.printResult(winningLotto.getPrizeCountMap(userLottos.getLottoNumbers()));
    }

    private int getPurchaseAmount() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        int amount = money.getLottoAmount();
        outputView.printPurchaseResult(amount);
        return amount;
    }
}
