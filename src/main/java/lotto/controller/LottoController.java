package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.AnswerLotto;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.Money;
import lotto.domain.NumberList;
import lotto.domain.PrizeGroupingMap;
import lotto.domain.RandomLottoGenerator;
import lotto.domain.SingleLottoNumber;
import lotto.domain.Store;
import lotto.dto.ResultDTO;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();

    private final OutputView outputView = new OutputView();

    public void play() {
        List<Lotto> lottoTickets = purchaseTicket();
        AnswerLotto answerLotto = getAnswerLotto();
        PrizeGroupingMap prizeGroupingMap = answerLotto.getPrizeCountMap(lottoTickets);
        outputView.printResult(ResultDTO.from(prizeGroupingMap));
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

    private List<Lotto> getManualLotto(LottoCount lottoCount) {
        outputView.printManualLottoGuide();
        List<Lotto> manualLottoList = new ArrayList<>();
        while (lottoCount.isManualLottoAvailable()) {
            NumberList userLottoInput = inputView.getUserLottoInput();
            manualLottoList.add(userLottoInput.toLotto());
            lottoCount.decreaseManualCount();
        }
        return manualLottoList;
    }

    private AnswerLotto getAnswerLotto() {
        NumberList answerLottoInput = inputView.getAnswerLottoInput();

        SingleLottoNumber bonusNumber = SingleLottoNumber.from(inputView.getBonusBallInput());

        return new AnswerLotto(answerLottoInput.toLotto(), bonusNumber);
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
}
