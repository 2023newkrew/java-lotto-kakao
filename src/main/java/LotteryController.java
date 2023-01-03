import buyer.Buyer;
import lotto.LotteryGenerator;
import lotto.LotteryResult;
import util.StringParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LotteryController {
    private final InputView inputView;
    private final OutputView outputView;

    public LotteryController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LotteryGenerator lotteryGenerator = new LotteryGenerator();

        int budget = inputView.getBudgetInput();
        Buyer buyer = new Buyer(budget);
        lotteryGenerator.generate(buyer);

        outputView.printLotteries(buyer.getLotteries());
        List<Integer> winningNumber = StringParser.parse(inputView.getWinningNumbersInput());
        int bonusNumber = inputView.getBonusNumberInput();

        LotteryResult lotteryResult = new LotteryResult(winningNumber, bonusNumber);

        outputView.printResult(lotteryResult.getResult(buyer.getLotteries()));
        outputView.printProfit(lotteryResult.getResult(buyer.getLotteries()).getProfit());
    }

}
