package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 45));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}