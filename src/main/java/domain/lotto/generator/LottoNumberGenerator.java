package domain.lotto.generator;

import domain.lotto.ticket.LottoNumber;

import java.util.List;

public class LottoNumberGenerator {
    public List<LottoNumber> makeNumbers(NumberGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
