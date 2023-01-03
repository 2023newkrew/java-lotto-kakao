package lotto.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public static Lotto createRandomLotto() {
        List<LottoNumber> numberPool = LottoNumber.createPool();
        Collections.shuffle(numberPool);
        return create(numberPool.subList(0, SIZE));
    }

    public static Lotto create(int... numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        return create(lottoNumbers);
    }

    public static Lotto create(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE || hasDuplicated(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없는 " + SIZE + "자리 숫자입니다.");
        }
        return new Lotto(numbers);
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

    public int countOverlappedNumber(Lotto other) {
        return (int) numbers.stream()
                .filter(other::hasNumber)
                .count();
    }

    public List<Integer> getLottoNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toList());
    }
}
