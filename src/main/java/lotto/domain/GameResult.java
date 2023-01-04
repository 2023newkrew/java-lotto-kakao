package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class GameResult {
    private final List<Integer> rankCount;
    private final double yield;

    public GameResult(List<Integer> rankCount, int lottoCount) {
        this.rankCount = rankCount;
        yield = calculateYield(rankCount, lottoCount);
    }

    public double calculateYield(List<Integer> rankCount, int lottoCount) {
        return (double) calculateTotalWinning(rankCount) / (lottoCount * LottoConstants.LOTTO_UNIT_PRICE);
    }

    private long calculateTotalWinning(List<Integer> rankCount) {
        return Arrays.stream(LottoRank.values()).mapToLong(rank -> rank.winning() * rankCount.get(rank.index())).sum();
    }

    public List<Integer> getRankCount() {
        return rankCount;
    }

    public double getYield() {
        return yield;
    }
}
