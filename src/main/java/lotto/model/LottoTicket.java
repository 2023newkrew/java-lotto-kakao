package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Stream<LottoNumber> stream() {
        return numbers.stream();
    }

    public List<Integer> toIntegerList() {
        return numbers.stream()
                .mapToInt(LottoNumber::getValue).sorted()
                .boxed().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "LottoTicket{" +
                "numbers=" + numbers +
                '}';
    }
}
