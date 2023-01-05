package lotto;

import lotto.controller.LottoGame;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
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
        List<LottoNumber> manual = inputView.inputManualNumbers(amount);
        LottoGame lottoGame = new LottoGame(amount, manual);

        // 구입한 로또 번호들 출력
        ResultView resultView = new ResultView();
        LottoTicket lottoTicket = lottoGame.getLottoTickets();
        resultView.printPurchaseCount(lottoTicket.getPurchaseCount(), manual.size());
        resultView.printLottoTickets(lottoTicket);

        // 지난주 당첨 번호 및 보너스볼 입력
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();

        // Winner 뽑아내기
        LottoWinnerTicket lottoWinnerTicket = inputView.changeToWinnerTicket(winNumber, bonus);
        Map<LottoRank, Integer> result = lottoWinnerTicket.getResult(lottoTicket);

        // 당첨 통계 출력
        double rate = lottoWinnerTicket.calcRateOfReturn(amount, result);
        resultView.printWinningStatics(result, rate);
    }
}
