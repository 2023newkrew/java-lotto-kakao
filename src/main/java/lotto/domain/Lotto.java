package lotto.domain;

import java.util.List;

public class Lotto {
    public static final int FIXED_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        if(numbers.size() != FIXED_SIZE){
            throw new RuntimeException("로또 수의 개수가 잘못되었습니다.");
        }
        if (numbers.stream().distinct().count() != FIXED_SIZE) {
            throw new RuntimeException("로또 수에 중복된 수가 있습니다.");
        }
        this.numbers = numbers;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public int compare(Lotto other) {
        return (int) numbers.stream().filter(other.numbers::contains).count();
    }

    public boolean hasBonus(LottoNumber other) {
        return numbers.contains(other);
    }
}
