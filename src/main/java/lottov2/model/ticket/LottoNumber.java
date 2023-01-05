package lottov2.model.ticket;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumber {

    public static final int SIZE = 6;

    private final Set<SingleLottoNumber> numbers;

    public static LottoNumber of(Set<SingleLottoNumber> numbers) {
        if (isInvalid(numbers)) {
            throw new IllegalArgumentException("로또 번호는 " + SIZE + "자리 숫자입니다.");
        }

        return new LottoNumber(numbers);
    }

    private LottoNumber(Set<SingleLottoNumber> numbers) {
        this.numbers = numbers;
    }

    private static boolean isInvalid(Set<SingleLottoNumber> numbers) {
        return Objects.isNull(numbers) || numbers.size() != SIZE;
    }

    public Set<Integer> getIntegers() {
        return numbers.stream()
                .map(SingleLottoNumber::intValue)
                .collect(Collectors.toSet());
    }
}
