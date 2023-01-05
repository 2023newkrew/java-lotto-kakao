package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTickets;
import lotto.domain.LottoWinnerTicket;
import lotto.domain.WinnerScore;
import lotto.service.LottoCalculator;
import lotto.utils.StringConversion;
import lotto.view.InputView;
import lotto.view.ResultView;

import static lotto.utils.Constants.MIN_PURCHASE_PRICE;

public class LottoApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int amount = inputView.inputUserAmount();
        int manualCount = inputView.inputManualCount(amount);
        amount -= manualCount * MIN_PURCHASE_PRICE;
        LottoTickets lottoTickets = inputView.inputManualNumbers(manualCount);

        // 로또 구매
        LottoController lottoController = new LottoController(amount);
        lottoController.registerManualLotto(lottoTickets);
        lottoController.registerRandomLotto(amount);
        ResultView resultView = new ResultView();
        resultView.printPurchaseCount(manualCount,amount);
        // 구입한 로또 번호들 출력
        resultView.printLottoTickets(lottoController.getLottoTickets());

        // 지난주 당첨 번호 및 보너스볼 입력
        StringConversion stringConversion = new StringConversion();

        // 보너스볼 입력
        LottoWinnerTicket lottoWinnerTicket = getLottoWinnerTicket(inputView, stringConversion);

        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);

        // Winner Score 뽑아내기
        WinnerScore winScore = lottoCalculator.getWinScore(lottoController.getLottoTickets());

        double rate = lottoCalculator.calcRateOfReturn(winScore, amount);
        resultView.printWinningStatics(winScore, rate);
    }

    private static LottoWinnerTicket getLottoWinnerTicket(InputView inputView, StringConversion stringConversion) {
        String winNumber = inputView.inputWinNumber();
        LottoNumber bonus = new LottoNumber(inputView.inputBonusNumber());
        return stringConversion.changeToWinnerTicket(
                winNumber, bonus);
    }
}
