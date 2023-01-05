package lotto;

import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.LottoSetting;
import lotto.domain.LottoWinningNumberList;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {

    public static void main(String[] args) {
        LottoSetting lottoSetting = new LottoSetting();
        LottoGame lottoGame = new LottoGame(lottoSetting);

        buyLotto(lottoGame);
        checkLotto(lottoGame);
    }

    private static void buyLotto(LottoGame lottoGame) {
        int money = InputView.getMoney();
        List<List<Integer>> numbers = InputView.getManualNumbersList();

        int manual = lottoGame.buyManually(money, numbers);
        int random = lottoGame.buyRandomly(lottoGame.receiveLeftoverMoney());
        ResultView.printQuantity(manual, random);
        ResultView.print(lottoGame.getLottoTicketsString());
    }

    private static void checkLotto(LottoGame lottoGame) {
        List<Integer> winningNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber();
        LottoWinningNumberList lottoWinningNumbers
                = new LottoWinningNumberList(winningNumbers, bonusNumber);

        ResultView.printStatistics(lottoGame.getWinningString(lottoWinningNumbers));
    }
}
