import buyer.Buyer;
import buyer.BuyerResult;
import lotto.LotteryGenerator;
import lotto.LotteryResult;
import util.StringParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LotteryController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LotteryGenerator lotteryGenerator = new LotteryGenerator();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());
        lotteryGenerator.generate(buyer);

        outputView.printLotteries(buyer.getLotteries());
        List<Integer> winningNumber = StringParser.parse(inputView.getWinningNumbersInput());
        int bonusNumber = inputView.getBonusNumberInput();

        LotteryResult lotteryResult = new LotteryResult(winningNumber, bonusNumber);
        BuyerResult buyerResult = lotteryResult.getResult(buyer.getLotteries());

        outputView.printResult(buyerResult);
        outputView.printProfit(buyerResult.getProfit());
    }

}
