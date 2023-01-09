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
        int totalAmount = inputView.inputUserAmount();
        LottoController lottoController = new LottoController(totalAmount);

        int manualCount = inputView.inputManualCount(totalAmount);
        int randomCount = (totalAmount - manualCount * MIN_PURCHASE_PRICE) / MIN_PURCHASE_PRICE;
        LottoTickets lottoTickets = inputView.inputManualNumbers(manualCount);

        lottoController.registerManualLotto(lottoTickets);
        lottoController.registerRandomLotto(randomCount);

        ResultView resultView = new ResultView();
        resultView.printPurchaseCount(manualCount,randomCount);
        resultView.printLottoTickets(lottoController.getLottoTickets());

        StringConversion stringConversion = new StringConversion();
        LottoWinnerTicket lottoWinnerTicket = getLottoWinnerTicket(inputView, stringConversion);

        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);
        WinnerScore winScore = lottoCalculator.getWinScore(lottoController.getLottoTickets());
        double rate = lottoCalculator.calcRateOfReturn(winScore, totalAmount);
        resultView.printWinningStatics(winScore, rate);
    }

    private static LottoWinnerTicket getLottoWinnerTicket(InputView inputView, StringConversion stringConversion) {
        String winNumber = inputView.inputWinNumber();
        LottoNumber bonus = new LottoNumber(inputView.inputBonusNumber());
        return stringConversion.changeToWinnerTicket(
                winNumber, bonus);
    }
}
