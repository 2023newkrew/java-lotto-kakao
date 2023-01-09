package lotto;

import lotto.controller.LottoGame;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;
import lotto.utils.LottoRank;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;
import java.util.Map;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int amount = inputView.inputUserAmount();

        // 로또 구매
        List<LottoTicket> manual = inputView.inputManualNumbers(amount);
        LottoGame lottoGame = new LottoGame(amount, manual);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        LottoTickets lottoTickets = lottoGame.getLottoTickets();
        resultView.printPurchaseCount(lottoTickets.getPurchaseCount(), manual.size());
        resultView.printLottoTickets(lottoTickets);

        // 지난주 당첨 번호 및 보너스볼 입력
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();

        // Winner 뽑아내기
        LottoWinnerTicket lottoWinnerTicket = inputView.changeToWinnerTicket(winNumber, bonus);
        Map<LottoRank, Integer> result = lottoWinnerTicket.getResult(lottoTickets);

        // 당첨 통계 출력
        double rate = lottoWinnerTicket.calcRateOfReturn(amount, result);
        resultView.printWinningStatics(result, rate);
    }
}
