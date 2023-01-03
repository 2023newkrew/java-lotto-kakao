package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    public static final int NUMBERS_SIZE = 6;
    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateValuesCount(numbers);
        validateDistinction(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateValuesCount(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDistinction(List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != NUMBERS_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    public List<Integer> toIntegerList() {
        List<Integer> values = new ArrayList<>();
        for (LottoNumber number : numbers) {
            values.add((number.getValue()));
        }
        Collections.sort(values);
        return values;
    }
}
