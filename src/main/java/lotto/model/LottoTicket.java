package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    public static final int NUMBERS_SIZE = 6;
    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateValuesCount(numbers);
        validateDistinction(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static LottoTicket fromNumbers(List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }

    private void validateValuesCount(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다");
        }
    }

    private void validateDistinction(List<LottoNumber> numbers) {
        if (numbers.stream().distinct().count() != NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 합니다");
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int match(LottoTicket ticket) {
        return (int) numbers.stream().filter(ticket::contains).count();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    @Override
    public String toString() {
        return "LottoTicket{" +
                "numbers=" + numbers +
                '}';
    }
}
