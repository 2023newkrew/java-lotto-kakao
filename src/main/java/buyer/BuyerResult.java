package buyer;

import lotto.LottoGenerator;
import lotto.Rank;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

public class BuyerResult {
    private final EnumMap<Rank, Integer> result;

    public BuyerResult() {
        result = new EnumMap<>(Rank.class);
    }

    public BuyerResult(EnumMap<Rank, Integer> result) {
        this.result = result;
    }

    public void matches(Rank matchResult) {
        result.put(matchResult, result.getOrDefault(matchResult, 0) + 1);
    }

    public EnumMap<Rank, Integer> getResult() {
        return result;
    }

    public double getProfit() {
        int profit = Arrays.stream(Rank.values())
                .mapToInt(e -> result.getOrDefault(e, 0) * e.prize)
                .sum();
        int count = Arrays.stream(Rank.values()).mapToInt(e -> result.getOrDefault(e, 0)).sum();

        return (double) profit / (count * Buyer.LOTTERY_PRICE);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BuyerResult)) return false;

        BuyerResult cp = (BuyerResult) obj;

        return this.result.equals(cp.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result);
    }
}
