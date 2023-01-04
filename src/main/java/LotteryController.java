import buyer.Buyer;
import buyer.BuyerProfit;
import buyer.BuyerResult;
import lotto.Lottery;
import lotto.LotteryResult;
import view.InputView;
import view.OutputView;

public class LotteryController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput());

        while (buyer.hasMoreBudgetThan(Lottery.PRICE)) {
            buyer.buyLottery(new Lottery());
        }
        outputView.printLotteries(buyer.getLotteries());

        LotteryResult lotteryResult =
                new LotteryResult(inputView.getWinningNumbersInput(), inputView.getBonusNumberInput());
        BuyerResult buyerResult = buyer.getBuyerResult(lotteryResult);

        outputView.printResult(buyerResult);
        outputView.printProfit(new BuyerProfit(buyer.getLotteriesCount(), buyerResult.getTotalPrize()));
    }
}
