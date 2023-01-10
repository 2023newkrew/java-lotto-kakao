package lotto.domain.lotto;

import lotto.domain.lotto.prize.LottoPrize;

import java.util.Map;

public class LottoResult {

    private final Map<LottoPrize, Integer> prizeCounts;
    private final double earningRate;

    public LottoResult(Map<LottoPrize, Integer> prizeCounts, double earningRate) {
        this.prizeCounts = prizeCounts;
        this.earningRate = earningRate;
    }

    public Map<LottoPrize, Integer> getPrizeCounts() {
        return prizeCounts;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
