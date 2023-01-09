package domain.lotto;

import java.util.Map;
import java.util.Objects;

public class LottoPrizeResult {

    private final Map<LottoPrize, Integer> prizeCounts;

    public LottoPrizeResult(Map<LottoPrize, Integer> prizeCounts) {
        validatePrizeCounts(prizeCounts);
        this.prizeCounts = prizeCounts;
    }

    public int calculatePrizeMoneySum() {
        return prizeCounts.entrySet().stream()
                .mapToInt(prizeCount -> prizeCount.getKey().getPrizeMoney() * prizeCount.getValue())
                .sum();
    }

    private void validatePrizeCounts(Map<LottoPrize, Integer> prizeCounts) {
        if (prizeCounts.size() != LottoPrize.values().length) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoPrizeResult that = (LottoPrizeResult) o;
        return Objects.equals(prizeCounts, that.prizeCounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeCounts);
    }

    public int getCountOf(LottoPrize lottoPrize) {
        return prizeCounts.get(lottoPrize);
    }
}
