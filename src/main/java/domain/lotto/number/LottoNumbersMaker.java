package domain.lotto.number;

import domain.dto.WinningNumbersDto;
import domain.lotto.number.generator.NumbersGeneratable;

import java.util.List;

public class LottoNumbersMaker {
    public List<Integer> makeNumbers(final NumbersGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
