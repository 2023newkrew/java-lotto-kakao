package lotto;

import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.Result;
import lotto.view.InputView;
import lotto.view.ResultView;

import java.util.ArrayList;

public class LottoMain {
    private static final int LOTTO_PRICE = 1000;

    public static void main(String[] args) {
        InputView iv = InputView.getInstance();
        ResultView rv = ResultView.getInstance();

        int money = iv.getMoneyInput();
        int count = money / LOTTO_PRICE;
        int exchange = money - count * LOTTO_PRICE;
        money = count * LOTTO_PRICE;

        LottoTickets tickets = LottoTickets.countOf(count);
        rv.printCountOfLottoTickets(tickets.getTicket().size());
        rv.printExchange(exchange);
        rv.printLottoTickets(tickets);

        String winningNumbers, bonusNumber;
        LottoTicket wn;
        do {
            winningNumbers = iv.getWinningNumbers();
            bonusNumber = iv.getBonusNumber();
            wn = createWinningNumbersInstance(winningNumbers, bonusNumber);
        } while (wn == null);

    private static ArrayList<String> getManualLotto(int count) {
        int manualCount = iv.getManualCount(count);
        validateManualCount(manualCount, count);
        ArrayList<String> manualLotto = iv.getManualLottoNumbers(manualCount);
        try {
            manualLotto.stream().map(Integer::parseInt);
        } catch (NumberFormatException e) {
            System.out.println("숫자와 구분자만 입력 가능 합니다.");
        }
        return manualLotto;
    }

        Result result = tickets.getResults(wn);
        rv.printResultStatistics(result, money);
    }

    private static LottoTicket createWinningNumbersInstance(String winningNumbers, String bonusNumber) {
        try {
            return new LottoTicket(winningNumbers, bonusNumber);
        } catch (Exception E) {
            System.out.println(E.getMessage());
            return null;
        }
    }
}
