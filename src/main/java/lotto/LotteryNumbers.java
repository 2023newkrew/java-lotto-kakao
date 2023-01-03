package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LotteryNumbers {
    private final List<LotteryNumber> numbers = new ArrayList<>();

    public LotteryNumbers(List<Integer> numbers) {
        for (Integer number : numbers) {
            this.numbers.add(new LotteryNumber(number));
        }
    }

    public List<LotteryNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryNumbers)) return false;

        LotteryNumbers cp = (LotteryNumbers) obj;

        return this.numbers.equals(cp.numbers);
    }
}
