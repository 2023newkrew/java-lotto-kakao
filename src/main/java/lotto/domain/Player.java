package lotto.domain;

import static lotto.constant.ExceptionMessage.BUDGET_NOT_POSITIVE;
import static lotto.constant.ExceptionMessage.NON_EXISTENT_SELF_PICK_COUNT;
import static lotto.constant.ExceptionMessage.NOT_DIVISIBLE_BUDGET;
import static lotto.constant.ExceptionMessage.SELF_PICK_COUNT_EXCEED_FORMAT;
import static lotto.constant.LotteryConstant.LOTTERY_COUNT_MINIMUM;
import static lotto.constant.LotteryConstant.LOTTERY_UNIT_PRICE;
import static lotto.constant.LotteryConstant.ZERO_MONEY;
import static lotto.constant.LotteryConstant.ZERO_REMAINDER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import lotto.domain.rank.LotteryRank;

public class Player {
    private int budget;
    private int selfPickCount;
    private final List<LotteryNumberCombination> lotteryTicket = new ArrayList<>();
    private List<Integer> rankCounts = new ArrayList<>();

    public int getSelfPickCount() {
        return selfPickCount;
    }

    public List<String> getLotteryTicket() {
        return lotteryTicket.stream()
                .map(LotteryNumberCombination::toString)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getRankCounts() {
        return new ArrayList<>(rankCounts);
    }

    public double getYield() {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / (calculateLotteryNumberCombinationCount() * LOTTERY_UNIT_PRICE);
    }

    public void setBudget(int budget) {
        validateBudget(budget);
        this.budget = budget;
    }

    public void setSelfPickCount(int count) {
        validateSelfPickCount(count);
        this.selfPickCount = count;
    }

    public void buySelfPicksWith(List<List<Integer>> selfPickNumbers) {
        lotteryTicket.addAll(LotteryVendingMachine.sellSelfPicksWith(selfPickNumbers));
    }

    public void buyQuickPicks() {
        int quickPickCount = calculateLotteryNumberCombinationCount() - selfPickCount;
        lotteryTicket.addAll(LotteryVendingMachine.sellQuickPicks(quickPickCount));
    }

    public void checkTicket(LotteryChecker lotteryChecker) {
        rankCounts = lotteryChecker.checkAll(lotteryTicket);
    }

    private int calculateLotteryNumberCombinationCount() {
        return budget / LOTTERY_UNIT_PRICE;
    }

    private long calculateTotalPrize() {
        return Arrays.stream(LotteryRank.values())
                .mapToLong(rank -> rank.getPrize() * rankCounts.get(rank.getIndex()))
                .sum();
    }

    private void validateBudget(int budget) {
        validateRange(budget);
        validateDivisibility(budget);
    }

    private void validateRange(int budget) {
        if (budget <= ZERO_MONEY) {
            throw new IllegalArgumentException(BUDGET_NOT_POSITIVE);
        }
    }

    private void validateDivisibility(int budget) {
        if (budget % LOTTERY_UNIT_PRICE != ZERO_REMAINDER) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_BUDGET);
        }
    }

    private void validateSelfPickCount(int count) {
        if (count < LOTTERY_COUNT_MINIMUM) {
            throw new IllegalArgumentException(NON_EXISTENT_SELF_PICK_COUNT);
        }
        if (count > calculateLotteryNumberCombinationCount()) {
            throw new IllegalArgumentException(String.format(SELF_PICK_COUNT_EXCEED_FORMAT,
                    calculateLotteryNumberCombinationCount()));
        }
    }
}
