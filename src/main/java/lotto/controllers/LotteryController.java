package lotto.controllers;

import static lotto.common.LotteryConfiguration.LOTTERY_PRICE;

import java.util.List;
import lotto.common.LotteryGenerator;
import lotto.models.Goal;
import lotto.models.Lottery;
import lotto.models.LotteryStatistics;
import lotto.views.Console;
import lotto.views.InputView;
import lotto.views.OutputView;

public class LotteryController {
    private final OutputView outputView;
    private final InputView inputView;

    public LotteryController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        Integer budget = getBudget();
        Integer numberOfLotteries = parseNumberOfLotteries(budget);
        List<Lottery> lotteries = createLotteryList(numberOfLotteries);
        Goal goal = getGoal();
        showStatistics(goal, lotteries, budget);
    }

    private Integer getBudget() {
        return outputView.requestUntilSuccess(this::getBudgetLogic);
    }

    private Integer getBudgetLogic() {
        outputView.askForMoneyToBuyLottery();
        return inputView.getInteger();
    }

    private Integer parseNumberOfLotteries(int budget) {
        Integer numberOfLotteries = budget / LOTTERY_PRICE;
        outputView.printNumberOfLottery(numberOfLotteries);
        return numberOfLotteries;
    }

    private List<Lottery> createLotteryList(int numberOfLottery) {
        List<Lottery> lotteries = LotteryGenerator.createLotteries(numberOfLottery);
        outputView.printLotteries(lotteries);
        return lotteries;
    }

    private Goal getGoal() {
        return outputView.requestUntilSuccess(this::getGoalLogic);
    }

    private Goal getGoalLogic() {
        List<Integer> goalNumbers = getGoalNumbers();
        Integer bonusBall = getBonusBall();
        return new Goal(goalNumbers, bonusBall);
    }

    private List<Integer> getGoalNumbers() {
        return outputView.requestUntilSuccess(this::getGoalNumbersLogic);
    }

    private List<Integer> getGoalNumbersLogic() {
        outputView.askForLastGoalNumbers();
        return inputView.getIntegerList();
    }

    private Integer getBonusBall() {
        return outputView.requestUntilSuccess(this::getBonusBallLogic);
    }

    private Integer getBonusBallLogic() {
        outputView.askForBonusBall();
        return inputView.getInteger();
    }

    private void showStatistics(Goal goal, List<Lottery> lotteries, Integer budget) {
        LotteryStatistics statistics = new LotteryStatistics(goal, lotteries, budget);
        outputView.printStatistics(statistics);
    }
}
