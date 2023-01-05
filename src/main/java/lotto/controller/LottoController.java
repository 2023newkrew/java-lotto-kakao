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
            // 구매할 로또 정보 입력
            int expenseInput = inputView.getExpenseInput();
            int totalAmount = LottoSeller.getLottoAmount(new Money(expenseInput));
            int manualAmount = inputView.getManualAmount();

            // 구매 로또 생성
            List<List<Integer>> manualNumbersGroup = inputView.getManualLottosInput(manualAmount);
            UserLottoTicket userLottoTicket = new UserLottoTicket(
                    LottoSeller.generateRandomLottos(totalAmount - manualAmount),
                    LottoSeller.generateManualLottos(manualNumbersGroup));

            // 구매 정보 출력
            outputView.printPurchaseAmount(totalAmount, manualAmount);
            outputView.printUserLottos(userLottoTicket.manualLottoTicket());
            outputView.printUserLottos(userLottoTicket.randomLottoTicket());

            // 정답 로또 입력 및 생성
            List<Integer> lottoNumbers = inputView.getAnswerLottoInput();
            LottoNumber bonusNumber = LottoNumber.from(inputView.getBonusBallInput());
            List<LottoNumber> answerLottoNumbers = lottoNumbers.stream()
                    .map(LottoNumber::from)
                    .collect(Collectors.toList());
            WinningLotto winningLotto = new WinningLotto(new LottoNumbers(answerLottoNumbers), bonusNumber);

            // 로또 결과 출력
            outputView.printResult(userLottoTicket.getLottoPrizeCountMap(winningLotto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play();
        }

    }

}
