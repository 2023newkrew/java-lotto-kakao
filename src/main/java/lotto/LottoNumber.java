package lotto;

import java.util.List;

public class LottoNumber {

    private final List<Integer> numbers;

    public LottoNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean hasNumber(int number){
        return numbers.contains(number);
    }

    public int countOverlappedNumber(LottoNumber other){
        return (int) numbers.stream()
                .filter(other::hasNumber)
                .count();
    }
}
