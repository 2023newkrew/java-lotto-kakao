package lotto.model;

import java.util.List;

public class Lotto {

    private static final String ERR_LOTTO_NUMBERS_ARE_NOT_SIX = "로또 번호는 6개의 숫자여야 합니다.";
    private static final String ERR_LOTTO_NUMBERS_ARE_UNIQUE = "로또 번호는 서로 다른 숫자로 이루어져야 합니다.";
    private static final int LOTTO_NUMBER_SIZE = 6;

    protected final List<LottoNumber> numbers;

    public Lotto(Integer... numbers) {
        this(List.of(numbers));
    }

    public Lotto(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERR_LOTTO_NUMBERS_ARE_NOT_SIX);
        }
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(ERR_LOTTO_NUMBERS_ARE_UNIQUE);
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