package lotto.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
        validateNumbers();
    }

    private void validateNumbers() {
        boolean isValidRangeAndLengthAndNoDuplicateNumbers = numbers.stream()
                .allMatch(number -> checkRange(number) && checkLength() && checkDuplicate());
        if (!isValidRangeAndLengthAndNoDuplicateNumbers) {
            throw new IllegalArgumentException();
        }
    }

    private Boolean checkRange(Integer number) {
        return LottoSettings.MIN_RANGE.getValue() <= number &&
                number <= LottoSettings.MAX_RANGE.getValue();
    }

    private Boolean checkLength() {
        return numbers.size() == LottoSettings.MAX_LENGTH.getValue();
    }

    private Boolean checkDuplicate() {
        return new HashSet<>(numbers)
                .size() == numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public Integer length() {
        return numbers.size();
    }

    public Integer get(int index) {
        return numbers.get(index);
    }

    @Override
    public String toString() {
        return "[" +
                numbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) +
                ']';
    }
}
