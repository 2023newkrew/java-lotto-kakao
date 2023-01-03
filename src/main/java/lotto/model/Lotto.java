package lotto.model;

import lotto.model.enums.LottoSettings;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
        validateNumbers();
    }

    private void validateNumbers() {
        boolean hasInvalidNumber = numbers.stream()
                .filter(number -> isOnRange(number) && hasValidLength())
                .collect(Collectors.toSet())
                .size() != numbers.size();

        if (hasInvalidNumber) {
            throw new IllegalArgumentException();
        }
    }

    private Boolean isOnRange(Integer number) {
        return number != null
                && number >= LottoSettings.MIN_RANGE.getValue()
                && number <= LottoSettings.MAX_RANGE.getValue();
    }

    private Boolean hasValidLength() {
        return numbers.size() == LottoSettings.NUMBER_LENGTH.getValue();
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
