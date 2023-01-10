package lotto.domain;

import java.util.List;

public class LottoNumbers {
    public static final int SIZE = 6;

    private List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        if(numbers.size() != SIZE
                || numbers.stream().distinct().count() != SIZE)
            throw new IllegalArgumentException("로또 번호가 이상합니다.");
        numbers.sort((a,b)->a.compare(b));
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public boolean contains(LottoNumber other) {
        return numbers.contains(other);
    }

    public int matchCount(LottoNumbers other) {
        return (int)numbers.stream().filter(number->other.contains(number)).count();
    }
}
