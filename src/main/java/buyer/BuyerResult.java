package buyer;

import lotto.Rank;

import java.util.EnumMap;

public class BuyerResult {
    private final EnumMap<Rank, Integer> result;

    public BuyerResult() {
        result = new EnumMap<>(Rank.class);
    }

    public BuyerResult(EnumMap<Rank, Integer> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BuyerResult)) return false;

        BuyerResult cp = (BuyerResult) obj;

        return this.result.equals(cp.result);
    }

    public void matches(Rank matchResult) {
        if (result.get(matchResult) == null) {
            result.put(matchResult, 1);

            return;
        }
        result.put(matchResult, result.get(matchResult) + 1);
    }

    public EnumMap<Rank, Integer> getResult() {
        return result;
    }
}
