package lotto;

import java.util.List;
import java.util.stream.Stream;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean hasNumber(int number){
        return numbers.contains(number);
    }

    public boolean hasBonus(int bonus) {
        return numbers.contains(bonus);
    }
}
