package lotto.model;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public Integer length() {
        return numbers.size();
    }

    public Integer getIthNumber(int index) {
        return numbers.get(index);
    }
}
