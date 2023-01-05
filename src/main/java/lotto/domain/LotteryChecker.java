package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.lotterynumber.LotteryNumberCombination;
import lotto.domain.lotterynumber.WinningNumbers;
import lotto.domain.rank.LotteryRank;

public class LotteryChecker {
    private final WinningNumbers winningNumbers;

    public LotteryChecker(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public LotteryRank check(LotteryNumberCombination lotteryNumberCombination) {
        int matchCount = lotteryNumberCombination.calculateMatchCount(
                winningNumbers.getWinningLotteryNumberCombination());
        boolean hasBonus = lotteryNumberCombination.contains(winningNumbers.getBonusLotteryNumber());
        return Arrays.stream(LotteryRank.values())
                .filter(rank -> rank.getRankCriteria().isSatisfiedBy(matchCount, hasBonus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public List<Integer> checkAll(List<LotteryNumberCombination> lotteryTicket) {
        List<Integer> rankCounts = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        lotteryTicket.stream()
                .map(lotteryNumberComb -> check(lotteryNumberComb).getIndex())
                .forEach(i -> rankCounts.set(i, rankCounts.get(i) + 1));
        return rankCounts;
    }
}
