package lotto.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<Integer> numbers;

    public static final Integer NUMBER_LENGTH = 6;

    public static final Integer PRICE = 1000;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
        validateNumbers();
    }

    private void validateNumbers() {
        boolean hasInvalidNumber = numbers.stream()
                .filter(number -> hasValidLength())
                .collect(Collectors.toSet())
                .size() != numbers.size();

        if (hasInvalidNumber) {
            throw new IllegalArgumentException();
        }
    }

    private Boolean hasValidLength() {
        return numbers.size() == NUMBER_LENGTH;
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
