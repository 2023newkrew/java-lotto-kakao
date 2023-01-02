package buyer;

import java.util.List;

public class BuyerResult {
    private final List<Integer> result;

    public enum Result{
        FIRST(4),
        SECOND(3),
        THIRD(2),
        FOURTH(1),
        FIFTH(0);

        private final int index;

        Result(int index) {
            this.index = index;
        }
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

    public void matches(Result matchResult) {
        result.set(matchResult.index, result.get(matchResult.index)+1);
    }
}
