package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    private static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public static LottoNumbers create(int... numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
        return create(lottoNumbers);
    }

    public static LottoNumbers create(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != SIZE || hasDuplicated(lottoNumbers)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없는 " + SIZE + "자리 숫자입니다.");
        }
        return new LottoNumbers(lottoNumbers);
    }

    private static boolean hasDuplicated(List<LottoNumber> numbers){
        return numbers.stream().distinct().count() != SIZE;
    }

    private LottoNumbers(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public boolean hasNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countOverlappedNumber(LottoNumbers other) {
        return (int) numbers.stream()
                .filter(other::hasNumber)
                .count();
    }
}
