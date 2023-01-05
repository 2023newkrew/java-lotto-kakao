package lotto;

import lotto.controller.LottoGame;
import lotto.domain.LottoTicket;
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
        LottoGame lottoGame = new LottoGame(amount);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        LottoTicket lottoTicket = lottoGame.getLottoTickets();
        resultView.printPurchaseCount(lottoTicket.getPurchaseCount());
        resultView.printLottoTickets(lottoTicket);

        // 지난주 당첨 번호 및 보너스볼 입력
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();

        // Winner 뽑아내기
        LottoWinnerTicket lottoWinnerTicket = inputView.changeToWinnerTicket(winNumber, bonus);
        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);
        ArrayList<Integer> result = lottoCalculator.getResult(lottoGame.getLottoTickets());

        // 당첨 통계 출력
        double rate = lottoCalculator.calcRateOfReturn(amount);
        resultView.printWinningStatics(result, rate);
    }
}
