import buyer.Buyer;
import buyer.BuyerResult;
import lotto.LottoGenerator;
import lotto.WinningLotto;
import util.StringParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());
        lottoGenerator.generate(buyer);

        outputView.printLotteries(buyer.getLotteries());
        List<Integer> winningNumber = StringParser.parse(inputView.getWinningNumbersInput());
        int bonusNumber = inputView.getBonusNumberInput();

        WinningLotto winningLotto = new WinningLotto(winningNumber, bonusNumber);
        BuyerResult buyerResult = winningLotto.getResult(buyer.getLotteries());

        outputView.printResult(buyerResult);
        outputView.printProfit(buyerResult.getProfit());
    }

}
