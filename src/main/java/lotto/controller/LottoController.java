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

    public int purchase() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        int amount = money.getLottoAmount();
        outputView.printPurchaseResult(amount);
        return amount;
    }

    public void play() {
        int amount = purchase();
        UserLottos userLottos = new UserLottos(RandomLottoGenerator.generateLottos(amount));
        outputView.printUserLottos(userLottos.getLottoNumbers());
        List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
        SingleLottoNumber bonusNumber = new SingleLottoNumber(inputView.getBonusBallInput());

        List<SingleLottoNumber> answerLottoNumbers = lottoNumbers.stream()
                .map(SingleLottoNumber::new)
                .collect(Collectors.toList());
        Lotto lotto = new Lotto(new LottoNumbers(answerLottoNumbers), bonusNumber);
        outputView.printResult(lotto.getPrizeCountMap(userLottos.getLottoNumbers()));
    }

}
