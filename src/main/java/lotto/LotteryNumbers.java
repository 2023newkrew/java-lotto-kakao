package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class LotteryNumbers {
    private static final int LOTTERY_NUMBER_SIZE = 6;

    private final Set<LotteryNumber> numbers = new HashSet<>();

    public LotteryNumbers(List<Integer> numbers) {
        for (Integer number : numbers) {
            this.numbers.add(new LotteryNumber(number));
        }

        validateNumbers();
    }

    private void validateNumbers() {
        if (numbers.size() != LOTTERY_NUMBER_SIZE)
            throw new IllegalArgumentException("잘못된 로또 번호 입력입니다");
    }

    public boolean contains(LotteryNumber number) {
        return numbers.contains(number);
    }

    public Stream<LotteryNumber> stream() {
        return numbers.stream();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LotteryNumbers)) return false;

        LotteryNumbers cp = (LotteryNumbers) obj;

        return this.numbers.equals(cp.numbers);
    }
}
