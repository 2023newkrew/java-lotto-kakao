package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.AnswerLotto;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.SingleLottoNumber;
import lotto.domain.Store;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private int purchaseTicket() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        Store store = new Store(money);
        int lottoTicketCount = store.getLottoAmount();
        outputView.printPurchaseResult(lottoTicketCount);
        return lottoTicketCount;
    }

    public void play() {
        int lottoTicketCount = purchaseTicket();
        List<Lotto> userLottos = getRandomLottoNumbers(lottoTicketCount);
        AnswerLotto answerLotto = getAnwerLotto();
        outputView.printResult(answerLotto.getPrizeCountMap(userLottos));
    }

    private AnswerLotto getAnwerLotto() {
        List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
        SingleLottoNumber bonusNumber = new SingleLottoNumber(inputView.getBonusBallInput());

        List<SingleLottoNumber> answerLottoNumbers = lottoNumbers.stream()
                .map(SingleLottoNumber::new)
                .sorted()
                .collect(Collectors.toList());

        return new AnswerLotto(new Lotto(answerLottoNumbers), bonusNumber);
    }

    private List<Lotto> getRandomLottoNumbers(int amount) {
        List<Lotto> userLottos = RandomLottoGenerator.generateLottoList(amount);
        outputView.printUserLottos(userLottos);
        return userLottos;
    }
}
