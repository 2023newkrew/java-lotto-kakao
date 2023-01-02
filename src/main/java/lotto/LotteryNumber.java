package lotto;

import java.util.Collections;
import java.util.List;

public class LotteryNumber {
    private final List<Integer> numbers;

    public LotteryNumber(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LotteryNumber)) return false;

        LotteryNumber cp = (LotteryNumber) obj;

        return this.numbers.equals(cp.numbers);
    }
}
