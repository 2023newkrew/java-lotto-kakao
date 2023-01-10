package lotto;

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

        int manualCount = inputView.inputManualCount(totalAmount);
        int randomCount = (totalAmount - manualCount * MIN_PURCHASE_PRICE) / MIN_PURCHASE_PRICE;
        LottoTickets lottoTickets = inputView.inputManualNumbers(totalAmount, manualCount);

        lottoTickets.registerRandomTicket(randomCount);

        ResultView resultView = new ResultView();
        resultView.printPurchaseCount(manualCount,randomCount);
        resultView.printLottoTickets(lottoTickets);

        StringConversion stringConversion = new StringConversion();
        LottoWinnerTicket lottoWinnerTicket = getLottoWinnerTicket(inputView, stringConversion);

        LottoCalculator lottoCalculator = new LottoCalculator(lottoWinnerTicket);
        WinnerScore winScore = lottoCalculator.getWinScore(lottoTickets);
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
