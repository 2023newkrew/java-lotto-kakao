package domain.lotto.number;

import java.util.List;

public class LottoNumberMaker {
    public List<Integer> makeNumbers(NumberGeneratable numberGeneratable) {
        return numberGeneratable.generate();
    }
}
