import buyer.Buyer;
import buyer.BuyerResult;
import lotto.LottoGenerator;
import lotto.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());
        lottoGenerator.generate(buyer);

        outputView.printLotteries(buyer.getLotteries());
        String winningNumbersAsString = inputView.getWinningNumbersInput();
        int bonusNumber = inputView.getBonusNumberInput();

        WinningLotto winningLotto = new WinningLotto(winningNumbersAsString, bonusNumber);
        BuyerResult buyerResult = winningLotto.getResult(buyer.getLotteries());

        outputView.printResult(buyerResult);
        outputView.printProfit(buyerResult.getProfit());
    }

}
