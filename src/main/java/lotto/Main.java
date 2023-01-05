package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();

        int purchaseAmount = inputView.scanPurchaseAmount();
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(purchaseAmount);
        outputView.printLottoTickets(lottoTickets);

        List<Integer> winningNumbers = inputView.scanWinningNumbers();
        int bonusNumber = inputView.scanBonusNumber();
        lottoMachine.setWinningNumber(winningNumbers, bonusNumber);

        MatchResult matchResult = lottoMachine.match(lottoTickets);
        outputView.printMatchResult(matchResult);
    }
}
