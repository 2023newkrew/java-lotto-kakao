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

        return getUserLottos(lottoTicketCount);
    }

    public void play() {
        List<Lotto> lottoTickets = purchaseTicket();
        AnswerLotto answerLotto = getAnswerLotto();
        outputView.printResult(answerLotto.getPrizeCountMap(lottoTickets));
    }

    private Lotto toLotto(List<Integer> lottoNumbers) {
        List<SingleLottoNumber> singleLottoNumberList = lottoNumbers.stream()
                .map(SingleLottoNumber::new)
                .sorted()
                .collect(Collectors.toList());

        return new Lotto(singleLottoNumberList);
    }

    private AnswerLotto getAnswerLotto() {
        List<Integer> answerLottoInput = inputView.getAnswerLottoInput();

        SingleLottoNumber bonusNumber = new SingleLottoNumber(inputView.getBonusBallInput());

        return new AnswerLotto(toLotto(answerLottoInput), bonusNumber);
    }

    private Lotto getLottoWithOption(int generateOption) {
        if (generateOption == 1) {
            return RandomLottoGenerator.generateLotto();
        }

        List<Integer> userLottoInput = inputView.getUserLottoInput();
        return toLotto(userLottoInput);
    }

    private List<Lotto> getUserLottos(int lottoTicketCount) {
        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            int generateOption = inputView.getGenerateOptionInput();
            Lotto lotto = getLottoWithOption(generateOption);
            userLottos.add(lotto);
        }
        outputView.printUserLottos(userLottos);
        return userLottos;
    }
}
