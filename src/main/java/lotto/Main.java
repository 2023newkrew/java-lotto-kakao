package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();

        int purchaseAmount = InputView.scanPurchaseAmount();
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(purchaseAmount);
        OutputView.printLottoTickets(lottoTickets);

        String winningNumberString = InputView.scanWinningNumberString();
        int bonusNumber = InputView.scanBonusNumber();
        lottoMachine.setWinningNumber(winningNumberString, bonusNumber);

        MatchResult matchResult = lottoMachine.match(lottoTickets);
        OutputView.printMatchResult(matchResult);
    }
}
