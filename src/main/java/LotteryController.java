import buyer.Buyer;
import buyer.BuyerResult;
import lotto.LotteryGenerator;
import lotto.LotteryResult;
import view.InputView;
import view.OutputView;

public class LotteryController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LotteryGenerator lotteryGenerator = new LotteryGenerator();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());
        lotteryGenerator.autoGenerate(buyer);
        outputView.printLotteries(buyer.getLotteries());

        LotteryResult lotteryResult =
                new LotteryResult(inputView.getWinningNumbersInput(), inputView.getBonusNumberInput());
        BuyerResult buyerResult = lotteryResult.getResult(buyer.getLotteries());

        outputView.printResult(buyerResult);
        outputView.printProfit(buyerResult.getProfit());
    }

}
