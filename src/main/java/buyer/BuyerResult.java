package buyer;

import lotto.Rank;

import java.util.ArrayList;
import java.util.List;

public class BuyerResult {
    private final List<Integer> result;

    public BuyerResult() {
        this(new ArrayList<>(List.of(0, 0, 0, 0, 0)));
    }

    public BuyerResult(List<Integer> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BuyerResult)) return false;

        BuyerResult cp = (BuyerResult) obj;

        return this.result.equals(cp.result);
    }

    public void matches(Rank matchResult) {
        result.set(matchResult.index, result.get(matchResult.index)+1);
    }
}
