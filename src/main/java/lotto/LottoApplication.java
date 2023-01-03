package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoWinnerTicket;
import lotto.service.LottoCalculator;
import lotto.utils.StringConversion;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;

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

        // Winner 뽑아내기
        ArrayList<Integer> result = lottoCalculator.getResult(lottoController.getLottoTickets());

        // 당첨 통계 출력
        double rate = lottoCalculator.calcRateOfReturn(amount);
        resultView.printWinningStatics(result, rate);
    }

    private static LottoWinnerTicket getLottoWinnerTicket(InputView inputView, StringConversion stringConversion) {
        String winNumber = inputView.inputWinNumber();
        int bonus = inputView.inputBonusNumber();
        return stringConversion.changeToWinnerTicket(
                winNumber, bonus);
    }
}
