package lotto.controllers;

import java.util.ArrayList;
import java.util.List;
import lotto.common.LotteryGenerator;
import lotto.models.Lottery;
import lotto.models.LotteryEarningsRate;
import lotto.models.LotteryStatistics;
import lotto.models.WinningLottery;
import lotto.views.Console;
import lotto.views.InputView;
import lotto.views.OutputView;

public class LotteryController {

    static public final Integer LOTTERY_PRICE = 1000;
    private final OutputView outputView;
    private final InputView inputView;

    public LotteryController(Console console) {
        outputView = new OutputView(console);
        inputView = new InputView(console);
    }

    public void run() {
        Integer budget = getBudget();
        Integer numberOfLotteries = getNumberOfLotteries(budget);
        Integer manualCount = getManualCount(numberOfLotteries);
        List<Lottery> lotteries = createLotteries(numberOfLotteries, manualCount);
        WinningLottery winningLottery = getWinningLotteries();
        showResult(winningLottery, lotteries, budget);
    }

    private Integer getBudget() {
        outputView.askForMoneyToBuyLottery();
        return outputView.requestUntilSuccess(this::getBudgetLogic);
    }

    private Integer getBudgetLogic() {
        return inputView.getPositiveInteger();
    }

    private Integer getManualCount(Integer numberOfLotteries) {
        return outputView.requestUntilSuccess(() -> getManualCountLogic(numberOfLotteries));
    }

    private Integer getManualCountLogic(Integer numberOfLotteries) {
        outputView.askForManualCount();
        Integer manualCount = inputView.getPositiveInteger();
        if (manualCount > numberOfLotteries) {
            throw new RuntimeException("입력하신 금액에 따라 " + numberOfLotteries + "개의 로또만 구매하실 수 있습니다.");
        }
        return manualCount;
    }

    private Integer getNumberOfLotteries(int budget) {
        return budget / LOTTERY_PRICE;
    }

    private List<Lottery> createLotteries(int numberOfLottery, int manualCount) {
        List<Lottery> lotteries = getManualLotteries(manualCount);
        outputView.printNumberOfLottery(numberOfLottery, manualCount);
        lotteries.addAll(LotteryGenerator.createLotteries(numberOfLottery - manualCount));
        outputView.printLotteries(lotteries);
        return lotteries;
    }

    private List<Lottery> getManualLotteries(Integer manualCount) {
        return outputView.requestUntilSuccess(() -> getManualLotteriesLogic(manualCount));
    }

    private List<Lottery> getManualLotteriesLogic(Integer manualCount) {
        outputView.askForManualLotteries();
        List<Lottery> lotteries = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            lotteries.add(LotteryGenerator.createLotteryManual(inputView.getIntegerList()));
        }
        return lotteries;
    }

    private WinningLottery getWinningLotteries() {
        return outputView.requestUntilSuccess(this::getWinningLotteriesLogic);
    }

    private WinningLottery getWinningLotteriesLogic() {
        List<Integer> goalNumbers = getWinningLotteriesNumbers();
        Integer bonusBall = getBonusBall();
        return new WinningLottery(goalNumbers, bonusBall);
    }

    private List<Integer> getWinningLotteriesNumbers() {
        return outputView.requestUntilSuccess(this::getWinningLotteriesNumbersLogic);
    }

    private List<Integer> getWinningLotteriesNumbersLogic() {
        outputView.askForLastGoalNumbers();
        return inputView.getIntegerList();
    }

    private Integer getBonusBall() {
        return outputView.requestUntilSuccess(this::getBonusBallLogic);
    }

    private Integer getBonusBallLogic() {
        outputView.askForBonusBall();
        return inputView.getPositiveInteger();
    }

    private void showResult(WinningLottery winningLottery, List<Lottery> lotteries, Integer budget) {
        LotteryStatistics statistics = getStatistics(winningLottery, lotteries);
        showEarningsRate(statistics, budget);
    }

    private LotteryStatistics getStatistics(WinningLottery winningLottery, List<Lottery> lotteries) {
        LotteryStatistics statistics = new LotteryStatistics(winningLottery, lotteries);
        outputView.printStatistics(statistics);
        return statistics;
    }

    private void showEarningsRate(LotteryStatistics lotteryStatistics, Integer budget) {
        LotteryEarningsRate lotteryEarningsRate = new LotteryEarningsRate(lotteryStatistics, budget);
        outputView.printEarningsRate(lotteryEarningsRate);
    }
}
