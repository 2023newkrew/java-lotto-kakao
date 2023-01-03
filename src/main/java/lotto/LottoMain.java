package lotto;

import lotto.model.LottoTickets;
import lotto.model.Result;
import lotto.model.WinningNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoMain {
    static int LOTTO_PRICE = 1000;

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
        winningNumbers = iv.getWinningNumbers();
        bonusNumber = iv.getBonusNumber();

        WinningNumbers wn = new WinningNumbers(winningNumbers, bonusNumber);

        Result result = tickets.getResults(wn);
        rv.printResultStatistics(result, money);
    }
}
