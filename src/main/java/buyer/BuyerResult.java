package buyer;

import java.util.List;

public class BuyerResult {
    private final List<Integer> result;

    public BuyerResult(List<Integer> result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BuyerResult)) return false;

        BuyerResult cp = (BuyerResult) obj;

        return this.result.equals(cp.result);
    }
}
