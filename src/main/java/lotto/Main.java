package lotto;

import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();

        int purchaseAmount = inputView.scanPurchaseAmount();

        int numberOfManualLotto = inputView.scanNumberOfManualLotto();
        List<List<Integer>> manualLottoNumbers = inputView.scanManualLottoNumbers(numberOfManualLotto);

        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(purchaseAmount, manualLottoNumbers);
        outputView.printLottoTickets(lottoTickets, numberOfManualLotto);

        List<Integer> winningNumbers = inputView.scanWinningNumbers();
        int bonusNumber = inputView.scanBonusNumber();
        lottoMachine.setWinningLotto(winningNumbers, bonusNumber);

        MatchResult matchResult = lottoMachine.match(lottoTickets);
        outputView.printMatchResult(matchResult);
    }
}
