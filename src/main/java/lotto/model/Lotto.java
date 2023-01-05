package lotto.model;

import java.util.List;

public class Lotto {

    protected final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = LottoNumber.of(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean contains(int number) {
        return numbers.stream()
                .anyMatch(v -> v.equals(LottoNumber.from(number)));
    }
}