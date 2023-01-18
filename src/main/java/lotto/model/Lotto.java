package lotto.model;

import java.util.List;

public class Lotto {

    private static final String ERR_LOTTO_NUMBERS_ARE_NOT_SIX = "로또 번호는 6개의 숫자여야 합니다.";

    protected final List<LottoNumber> numbers;

    public Lotto(Integer... numbers) {
        this(List.of(numbers));
    }

    public Lotto(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException(ERR_LOTTO_NUMBERS_ARE_NOT_SIX);
        }
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