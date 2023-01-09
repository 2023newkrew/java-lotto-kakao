package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public int getMatchCount(Lotto lotto) {
        return (int) numbers.stream()
                .filter(lotto::containsNumber)
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    public String lottoToString() {
        return numbers.stream()
                .map(number -> number.number)
                .collect(Collectors.toList())
                .toString();
    }

}
