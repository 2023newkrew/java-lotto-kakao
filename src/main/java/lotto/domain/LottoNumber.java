package lotto.domain;

import java.util.List;

public class LottoNumber {
    public static final int FIXED_SIZE = 6;
    public static final int MINIMUM_BOUNDARY = 1;
    public static final int MAXIMUM_BOUNDARY = 45;

    private final List<Integer> numbers;

    public LottoNumber(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateNumbers(List<Integer> numbers) throws RuntimeException {
        if(numbers.size() != FIXED_SIZE){
            throw new RuntimeException();
        }

        if(numbers.stream()
                .anyMatch(number -> number < MINIMUM_BOUNDARY || number > MAXIMUM_BOUNDARY)) {
            throw new RuntimeException();
        }

        if(numbers.stream().distinct().count() != 6) {
            throw new RuntimeException();
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
