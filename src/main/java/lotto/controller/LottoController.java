package lotto.controller;

import java.util.ArrayList;
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

    private List<Lotto> purchaseTicket() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        Store store = new Store(money);

        int lottoTicketCount = store.getLottoAmount();
        outputView.printPurchaseResult(lottoTicketCount);

        return getRandomLottoNumbers(lottoTicketCount);
    }

    public void play() {
        List<Lotto> lottoTickets = purchaseTicket();
        AnswerLotto answerLotto = getAnswerLotto();
        outputView.printResult(answerLotto.getPrizeCountMap(lottoTickets));
    }

    private AnswerLotto getAnswerLotto() {
        List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
        SingleLottoNumber bonusNumber = new SingleLottoNumber(inputView.getBonusBallInput());

        List<SingleLottoNumber> answerLottoNumbers = lottoNumbers.stream()
                .map(SingleLottoNumber::new)
                .sorted()
                .collect(Collectors.toList());

        return new AnswerLotto(new Lotto(answerLottoNumbers), bonusNumber);
    }

    private List<Lotto> getRandomLottoNumbers(int lottoTicketCount) {
        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            userLottos.add(RandomLottoGenerator.generateLotto());
        }
        outputView.printUserLottos(userLottos);
        return userLottos;
    }
}
