package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumbers;
import lotto.domain.Money;
import lotto.domain.SingleLottoNumber;
import lotto.domain.Store;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private int purchase() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        Store store = new Store(money);
        int amount = store.getLottoAmount();
        outputView.printPurchaseResult(amount);
        return amount;
    }

    public void play() {
        int amount = purchase();
        List<LottoNumbers> userLottos = getRandomLottoNumbers(amount);
        Lotto lotto = getAnwerLotto();
        outputView.printResult(lotto.getPrizeCountMap(userLottos));
    }

    private Lotto getAnwerLotto() {
        List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
        SingleLottoNumber bonusNumber = new SingleLottoNumber(inputView.getBonusBallInput());

        List<SingleLottoNumber> answerLottoNumbers = lottoNumbers.stream()
                .map(SingleLottoNumber::new)
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(new LottoNumbers(answerLottoNumbers), bonusNumber);
    }

    private List<LottoNumbers> getRandomLottoNumbers(int amount) {
        List<LottoNumbers> userLottos = RandomLottoGenerator.generateLottos(amount);
        outputView.printUserLottos(userLottos);
        return userLottos;
    }
}
