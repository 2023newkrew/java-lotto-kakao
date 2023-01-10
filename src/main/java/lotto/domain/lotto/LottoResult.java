package lotto.domain.lotto;

import lotto.domain.lotto.prize.LottoPrize;

import java.math.BigDecimal;
import java.util.Map;

public class LottoResult {

    private final Map<LottoPrize, Integer> prizeCounts;
    private final BigDecimal earningRate;

    public LottoResult(Map<LottoPrize, Integer> prizeCounts, BigDecimal earningRate) {
        this.prizeCounts = prizeCounts;
        this.earningRate = earningRate;
    }

    public Map<LottoPrize, Integer> getPrizeCounts() {
        return prizeCounts;
    }

    public BigDecimal getEarningRate() {
        return earningRate;
    }
}
