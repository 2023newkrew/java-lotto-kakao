package lotto;

import java.util.Collections;
import java.util.List;

public class LotteryNumber {
    private final List<Integer> numbers;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    public LotteryNumber(List<Integer> numbers){
        for(final Integer number : numbers) validateNumber(number);

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new RuntimeException("잘못 된 로또 번호");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LotteryNumber)) return false;

        LotteryNumber cp = (LotteryNumber) obj;

        return this.numbers.equals(cp.numbers);
    }
}
