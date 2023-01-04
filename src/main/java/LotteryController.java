import buyer.Buyer;
import buyer.BuyerProfit;
import buyer.BuyerResult;
import lotto.AutoLotteryNumber;
import lotto.Lottery;
import lotto.LotteryNumbers;
import lotto.LotteryResult;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LotteryController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final AutoLotteryNumber autoLotteryNumber = new AutoLotteryNumber();

    public void run() {
        Buyer buyer = new Buyer(inputView.getBudgetInput(), inputView.getManualCount());

        buyLottery(buyer);

        LotteryResult lotteryResult = inputView.getLotteryResult();

        BuyerResult buyerResult = buyer.getBuyerResult(lotteryResult);

        outputView.printResult(buyerResult);
        outputView.printProfit(new BuyerProfit(buyer.getTotalLotteryCount(), buyerResult.getTotalPrize()));
    }

    private void buyLottery(Buyer buyer) {
        List<LotteryNumbers> manualNumbers = inputView.getManualLotteries(buyer.getManualCount());

        for (LotteryNumbers numbers : manualNumbers) {
            buyer.buyLottery(new Lottery(numbers));
        }
        for (int i = 0, n = buyer.getAutoCount(); i < n; ++i) {
            buyer.buyLottery(new Lottery(autoLotteryNumber.generate()));
        }

        outputView.printLotteriesInfo(buyer);
        outputView.printLotteries(buyer.getLotteries());
    }
}