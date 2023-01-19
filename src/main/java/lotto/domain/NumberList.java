package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberList {

    private final List<Integer> numbers;

    public NumberList(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public Lotto toLotto() {
        return new Lotto(this.numbers.stream()
                .map(SingleLottoNumber::from)
                .collect(Collectors.toList()));
    }
}
