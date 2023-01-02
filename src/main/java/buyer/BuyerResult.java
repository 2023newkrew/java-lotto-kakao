package buyer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyerResult {
    private final List<Integer> result;

    public enum Result{
        FIRST(4,6),
        SECOND(3,5),
        THIRD(2,5),
        FOURTH(1,4),
        FIFTH(0,3);

        private final int index;
        private final int matches;

        Result(int index, int matches) {
            this.index = index;
            this.matches = matches;
        }

        public static Result getResult(int match){
            return Arrays.stream(values())
                    .filter(Result -> Result.matches == match)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

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

    public void matches(Result matchResult) {
        result.set(matchResult.index, result.get(matchResult.index)+1);
    }
}
