package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoWinnerTicket;
import lotto.service.LottoCalculator;
import lotto.utils.StringConversion;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.List;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int amount = inputView.inputUserAmount();

        ResultView resultView = new ResultView();
        resultView.printPurchaseCount(amount);

        // 로또 구매
        LottoController lottoController = new LottoController(amount);

        // 구입한 로또 번호들 출력
        resultView.printLottoTickets(lottoController.getLottoTickets());

        // 지난주 당첨 번호 및 보너스볼 입력
        StringConversion stringConversion = new StringConversion();

        // 보너스볼 입력
        LottoWinnerTicket lottoWinnerTicket = getLottoWinnerTicket(inputView, stringConversion);

        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);

        // Winner Score 뽑아내기
        List<Integer> winScore = lottoCalculator.getWinScore(lottoController.getLottoTickets());

        double rate = lottoCalculator.calcRateOfReturn(winScore, amount);
        resultView.printWinningStatics(winScore, rate);
    }

    private static LottoWinnerTicket getLottoWinnerTicket(InputView inputView, StringConversion stringConversion) {
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();
        return stringConversion.changeToWinnerTicket(
                winNumber, bonus);
    }
}
