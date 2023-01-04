package lotto.models;

import static lotto.common.LotteryConfiguration.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lottery {
    private final List<Integer> numbers;

    public Lottery(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream().sorted().collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        validateLotteryCount(numbers);
        for (Integer number : numbers) {
            validateLotteryNumberRange(number);
        }
        validateLotteryNumberIsNotDuplicated(numbers);
    }

    private void validateLotteryNumberRange(Integer number) {
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new RuntimeException("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

    private void validateLotteryCount(List<Integer> numbers) {
        if (numbers.size() != LOTTERY_COUNT) {
            throw new RuntimeException("로또는" + LOTTERY_COUNT + "개의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateLotteryNumberIsNotDuplicated(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new RuntimeException("로또 번호에 중복된 번호가 있어서는 안됩니다.");
        }
    }
}
