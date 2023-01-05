package lotto.domain.lotterynumber;

import static lotto.constant.ExceptionMessage.LOTTERY_NUMBERS_WRONG_DISTINCT_COUNT;
import static lotto.constant.LotteryConstant.LOTTERY_NUMBERS_LENGTH;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LotteryNumberCombination {
    private final Set<LotteryNumber> lotteryNumbers;

    public LotteryNumberCombination(List<Integer> lotteryNumbers) {
        this(lotteryNumbers.stream()
                .map(LotteryNumber::of)
                .collect(Collectors.toSet()));
    }

    public LotteryNumberCombination(Set<LotteryNumber> lotteryNumbers) {
        validate(lotteryNumbers);
        this.lotteryNumbers = lotteryNumbers;
    }

    private void validate(Set<LotteryNumber> lotteryNumbers) {
        if (lotteryNumbers.size() != LOTTERY_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(LOTTERY_NUMBERS_WRONG_DISTINCT_COUNT);
        }
    }

    public int calculateMatchCount(LotteryNumberCombination lotteryNumberCombination) {
        return (int) lotteryNumbers.stream()
                .filter(lotteryNumberCombination.lotteryNumbers::contains)
                .count();
    }

    public boolean contains(int value) {
        return contains(LotteryNumber.of(value));
    }

    public boolean contains(LotteryNumber lotteryNumber) {
        return lotteryNumbers.contains(lotteryNumber);
    }

    @Override
    public String toString() {
        return lotteryNumbers.stream()
                .map(LotteryNumber::getValue)
                .sorted()
                .collect(Collectors.toList())
                .toString();
    }
}
