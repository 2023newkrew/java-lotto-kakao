package lotto.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lottery {

    static public final Integer LOTTERY_COUNT = 6;
    private final List<LotteryNumber> numbers;

    public Lottery(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
    }

    public List<LotteryNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public boolean contains(LotteryNumber number) {
        return numbers.contains(number);
    }

    public Integer compare(Lottery lottery) {
        return (int) numbers.stream().filter(lottery::contains).count();
    }

    public String getNumbersString() {
        return "["
                + numbers.stream().map((number) -> number.getNumber().toString())
                .collect(Collectors.joining(", "))
                + "]";
    }

    private void validateNumbers(List<Integer> numbers) {
        validateLotteryCount(numbers);
        validateLotteryNumberIsNotDuplicated(numbers);
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
