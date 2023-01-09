package domain.lotto;

import java.util.Objects;

public class LottoEarningRate {
    public static final double MINIMUM_EARNING_RATE = 0.0;
    private final double earningRate;

    public LottoEarningRate(double earningRate) {
        validateEarningRate(earningRate);
        this.earningRate = earningRate;
    }

    public LottoEarningRate(LottoPrizeResult lottoPrizeResult, LottoTickets lottoTickets) {
        int prizeMoneySum = lottoPrizeResult.calculatePrizeMoneySum();
        Cost cost = lottoTickets.calculatePurchaseCost();
        double tempEarningRate = (double) prizeMoneySum / cost.getCost();
        validateEarningRate(tempEarningRate);
        this.earningRate = tempEarningRate;
    }

    public double getEarningRate() {
        return earningRate;
    }

    private void validateEarningRate(double earningRate) {
        if (earningRate < MINIMUM_EARNING_RATE) {
            throw new IllegalArgumentException("수익률은 0 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoEarningRate that = (LottoEarningRate) o;
        return Double.compare(that.earningRate, earningRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(earningRate);
    }
}
