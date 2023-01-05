package lotto.view;

import static lotto.constant.LotteryConstant.ZERO_MONEY;
import static lotto.domain.rank.IsBonusRequired.TRUE;
import static lotto.view.OutputViewMessage.MATCH_COUNT_STRING_FORMAT;
import static lotto.view.OutputViewMessage.PRINT_GAME_RESULT_MESSAGE;
import static lotto.view.OutputViewMessage.PRINT_LOTTERY_COUNT_MESSAGE_FORMAT;
import static lotto.view.OutputViewMessage.PRINT_RANK_REMAINING_MESSAGE_FORMAT;
import static lotto.view.OutputViewMessage.PRINT_YIELD_MESSAGE_FORMAT;
import static lotto.view.OutputViewMessage.READ_BONUS_NUMBER_MESSAGE;
import static lotto.view.OutputViewMessage.READ_BUDGET_MESSAGE;
import static lotto.view.OutputViewMessage.READ_SELF_PICK_COUNT_MESSAGE;
import static lotto.view.OutputViewMessage.READ_SELF_PICK_NUMBERS_MESSAGE;
import static lotto.view.OutputViewMessage.READ_WINNING_LOTTERY_NUMBER_COMBINATION_MESSAGE;
import static lotto.view.OutputViewMessage.REQUIRES_BONUS_STRING;

import java.util.Arrays;
import java.util.List;
import lotto.domain.rank.LotteryRank;

public class OutputView {
    public void printReadBudget() {
        System.out.println(READ_BUDGET_MESSAGE);
    }

    public void printReadSelfPickCount() {
        System.out.println(READ_SELF_PICK_COUNT_MESSAGE);
    }

    public void printReadSelfPickNumbers() {
        System.out.println(READ_SELF_PICK_NUMBERS_MESSAGE);
    }

    public void printLotteryTicket(int selfPickCount, List<String> lotteryTicket) {
        int quickPickCount = lotteryTicket.size() - selfPickCount;
        System.out.printf(PRINT_LOTTERY_COUNT_MESSAGE_FORMAT, selfPickCount, quickPickCount);
        lotteryTicket.forEach(System.out::println);
        System.out.println();
    }

    public void printReadWinningLotteryNumberCombination() {
        System.out.println(READ_WINNING_LOTTERY_NUMBER_COMBINATION_MESSAGE);
    }

    public void printReadBonusNumber() {
        System.out.println(READ_BONUS_NUMBER_MESSAGE);
    }

    public void printResult(List<Integer> rankCounts, double yield) {
        System.out.println(PRINT_GAME_RESULT_MESSAGE);
        Arrays.stream(LotteryRank.values())
                .filter(rank -> rank.getPrize() > ZERO_MONEY)
                .sorted((rank1, rank2) -> Integer.compare(rank2.getIndex(), rank1.getIndex()))
                .forEach(rank -> printSingleRankResult(rank, rankCounts.get(rank.getIndex())));
        System.out.printf(PRINT_YIELD_MESSAGE_FORMAT, yield);
    }

    private void printSingleRankResult(LotteryRank rank, int count) {
        System.out.printf(MATCH_COUNT_STRING_FORMAT, rank.getMinMatchCount());
        if (rank.isBonusRequired() == TRUE) {
            System.out.print(REQUIRES_BONUS_STRING);
        }
        System.out.printf(PRINT_RANK_REMAINING_MESSAGE_FORMAT, rank.getPrize(), count);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
