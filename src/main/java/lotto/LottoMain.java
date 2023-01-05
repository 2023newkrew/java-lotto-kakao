package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumberList;
import lotto.domain.WinningLotto;
import lotto.strategy.RandomAutoNumberSelectStrategy;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {

    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame(new RandomAutoNumberSelectStrategy());

        buyLotto(lottoGame);
        checkLotto(lottoGame);
    }

    private static void buyLotto(LottoGame lottoGame) {
        long money = InputView.getMoney();
        long manualTicketQuantity = InputView.getManualTicketQuantity(money);
        List<LottoNumberList> manualLottoNumberList = getManualLottoNumberList(manualTicketQuantity);
        lottoGame.buyLottoTickets(money, manualLottoNumberList);
        ResultView.printQuantity(lottoGame.getQuantityOfLottoTickets());
        ResultView.print(lottoGame.getLottoTicketsString());
    }

    private static List<LottoNumberList> getManualLottoNumberList(long manualTicketQuantity) {
        List<LottoNumberList> manualLottoNumberList = new ArrayList<>();
        for (int i = 0; i < manualTicketQuantity; i++) {
            List<Integer> manualLottoNumbers = InputView.getManualLottoNumbers();
            manualLottoNumberList.add(new LottoNumberList(manualLottoNumbers));
        }
        return manualLottoNumberList;
    }

    private static void checkLotto(LottoGame lottoGame) {
        List<Integer> winningNumbers = InputView.getWinningNumbers();
        int bonusNumber = InputView.getBonusNumber();
        WinningLotto winningLotto
                = new WinningLotto(winningNumbers, bonusNumber);

        ResultView.print(lottoGame.getLottoGameResult(winningLotto));
    }
}
