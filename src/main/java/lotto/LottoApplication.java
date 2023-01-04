package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;
import lotto.service.LottoCalculator;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int amount = inputView.inputUserAmount();

        // 로또 구매
        LottoController lottoController = new LottoController(amount);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        LottoTickets lottoTickets = lottoController.getLottoTickets();
        resultView.printPurchaseCount(lottoTickets.getPurchaseCount());
        resultView.printLottoTickets(lottoTickets);

        // 지난주 당첨 번호 및 보너스볼 입력
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();

        // Winner 뽑아내기
        LottoWinnerTicket lottoWinnerTicket = inputView.changeToWinnerTicket(winNumber, bonus);
        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);
        ArrayList<Integer> result = lottoCalculator.getResult(lottoController.getLottoTickets());

        // 당첨 통계 출력
        double rate = lottoCalculator.calcRateOfReturn(amount);
        resultView.printWinningStatics(result, rate);
    }
}
