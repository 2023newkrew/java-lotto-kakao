package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.AnswerLotto;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.Money;
import lotto.domain.SingleLottoNumber;
import lotto.domain.Store;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();

    private final OutputView outputView = new OutputView();

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

    private List<Lotto> purchaseTicket() {
        int expenseInput = inputView.getExpenseInput();
        Money money = new Money(expenseInput);
        Store store = new Store(money);

        int lottoTicketCount = store.calculateTicketCount();
        int manualLottoCount = inputView.getManualLottoCountInput();
        LottoCount lottoCount = new LottoCount(manualLottoCount, lottoTicketCount);

        outputView.printPurchaseResult(manualLottoCount, lottoTicketCount - manualLottoCount);

        return getUserLottoList(lottoCount);
    }

    private List<Lotto> getUserLottoList(LottoCount lottoCount) {
        List<Lotto> userLottoList = new ArrayList<>();
        userLottoList.addAll(getManualLotto(lottoCount));
        userLottoList.addAll(getAutoLotto(lottoCount));

        outputView.printUserLottos(userLottoList);
        return userLottoList;
    }

    private List<Lotto> getAutoLotto(LottoCount lottoCount) {
        List<Lotto> autoLottoList = new ArrayList<>();
        while (lottoCount.isAutoLottoAvailable()) {
            Lotto randomLotto = RandomLottoGenerator.generateLotto();
            autoLottoList.add(randomLotto);
            lottoCount.decreaseAutoCount();
        }
        return autoLottoList;
    }

    private List<Lotto> getManualLotto(LottoCount lottoCount) {
        outputView.printManualLottoGuide();
        List<Lotto> manualLottoList = new ArrayList<>();
        while (lottoCount.isManualLottoAvailable()) {
            List<Integer> userLottoInput = inputView.getUserLottoInput();
            manualLottoList.add(toLotto(userLottoInput));
            lottoCount.decreaseManualCount();
        }
        return manualLottoList;
    }
}
