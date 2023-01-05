package lotto.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int SIZE = 6;

    private final Set<LottoNumber> numbers;

    private Lotto(Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto createRandomLotto() {
        List<LottoNumber> numberPool = LottoNumber.createPool();
        Collections.shuffle(numberPool);
        Set<LottoNumber> numbers = new HashSet<>(numberPool.subList(0, SIZE));
        return create(numbers);
    }

    public static Lotto create(int... numbers) {
        Set<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toSet());
        return create(lottoNumbers);
    }

    public static Lotto create(Set<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복이 없는 " + SIZE + "자리 숫자입니다.");
        }
        return new Lotto(numbers);
    }

    public boolean hasNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public long countOverlappedNumber(Lotto other) {
        return numbers.stream()
                .filter(other::hasNumber)
                .count();
    }

    public List<Integer> getLottoNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
