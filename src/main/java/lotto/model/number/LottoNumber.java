package lotto.model.number;

import lotto.common.exception.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    public static final Integer MIN_VALUE = 1;
    public static final Integer MAX_VALUE = 45;
    public static final Integer LOTTO_COUNT = 6;
    private static final List<Integer> lottoPreset = initializeLottoPreset();

    public static LottoNumber create() {
        Collections.shuffle(lottoPreset);
        List<Integer> numbers = lottoPreset.subList(0, LOTTO_COUNT).stream()
                .sorted()
                .collect(Collectors.toList());

        return new LottoNumber(numbers);
    }

    public static LottoNumber create(List<Integer> numbers) {
        return new LottoNumber(numbers);
    }

    private static List<Integer> initializeLottoPreset() {
        return IntStream
                .rangeClosed(MIN_VALUE, MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    private final List<Integer> numbers;

    LottoNumber(List<Integer> numbers) {
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
        if (number < MIN_VALUE || number > MAX_VALUE) {
            throw new InvalidInputException("로또는 " + MIN_VALUE + "부터" + MAX_VALUE + "사이의 수 이어야 합니다.");
        }
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
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
