package domain.lotto.generator;

import domain.lotto.generator.NumberGeneratable;

import java.util.List;

public class LottoNumberGenerator {
    public List<Integer> makeNumbers(NumberGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
