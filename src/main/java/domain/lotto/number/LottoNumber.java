package domain.lotto.number;

import domain.lotto.generator.NumberGeneratable;

import java.util.List;

public class LottoNumber {
    public List<Integer> makeNumbers(NumberGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
