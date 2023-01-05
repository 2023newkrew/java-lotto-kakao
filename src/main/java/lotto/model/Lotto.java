package lotto.model;

import lotto.model.errors.LottoDuplicatedNumberException;
import lotto.model.errors.LottoOutOfRangeException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final Integer NUMBER_LENGTH = 6;

    public static final Integer PRICE = 1000;

    private final Set<LottoNumber> numbers;

    private Lotto(Set<LottoNumber> numbers) {
        this.numbers = numbers;
        validateAfterDistinct();
    }

    public static Lotto of(Integer... numbers) {
        validateNumberLength(numbers);
        return new Lotto(Arrays.stream(numbers)
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
    }

    public static Lotto of(List<Integer> numbers) {
        validateNumberLength(numbers);
        return new Lotto(numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet()));
    }

    private static void validateNumberLength(Integer[] numbers) {
        if (numbers.length != NUMBER_LENGTH) {
            throw new LottoOutOfRangeException(String.format("로또 숫자는 %d개여야 합니다.", Lotto.NUMBER_LENGTH));
        }
    }

    private static void validateNumberLength(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new LottoOutOfRangeException(String.format("로또 숫자는 %d개여야 합니다.", Lotto.NUMBER_LENGTH));
        }
    }

    private void validateAfterDistinct() {
        if (!hasValidLength()) {
            throw new LottoDuplicatedNumberException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private Boolean hasValidLength() {
        return numbers.size() == NUMBER_LENGTH;
    }

    public Set<LottoNumber> getNumbers() {
        return Collections.unmodifiableSet(numbers);
    }

    public Integer length() {
        return numbers.size();
    }

    @Override
    public String toString() {
        return "[" +
                numbers.stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) +
                ']';
    }
}
