package lotto.model.number;

import lotto.common.exception.InvalidInputException;

import static lotto.common.LottoConfiguration.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumber {
    private final List<Integer> numbers;

    public LottoNumber(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        validateLottoCount(numbers);
        numbers.forEach(this::validateLottoNumberRange);
        validateLottoNumberIsNotDuplicated(numbers);
    }

    private void validateLottoNumberRange(Integer number) {
        if (number < MIN_VALUE.getValue() || number > MAX_VALUE.getValue()) {
            throw new InvalidInputException("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT.getValue()) {
            throw new InvalidInputException("로또는" + LOTTO_COUNT + "개의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateLottoNumberIsNotDuplicated(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numbers.size() != numberSet.size()) {
            throw new InvalidInputException("로또 번호에 중복된 번호가 있어서는 안됩니다.");
        }
    }
}
