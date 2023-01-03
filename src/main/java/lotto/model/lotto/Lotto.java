package lotto.model.lotto;

import lotto.model.vo.LottoNumber;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {

    public static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public static Lotto from(List<LottoNumber> numbers) {
        if (isInvalid(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없는 " + SIZE + "자리 숫자입니다.");
        }

        return new Lotto(numbers);
    }

    private static boolean isInvalid(List<LottoNumber> numbers) {
        return Objects.isNull(numbers) || numbers.size() != SIZE || hasDuplicated(numbers);
    }

    private static boolean hasDuplicated(List<LottoNumber> numbers) {
        return numbers.stream().distinct().count() != SIZE;
    }

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public boolean hasNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public long countCommonNumber(Lotto other) {
        return numbers.stream()
                .filter(other::hasNumber)
                .count();
    }

    public List<Integer> getLottoNumbers() {
        return numbers.stream()
                .map(LottoNumber::intValue)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
