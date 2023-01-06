package lotto.util;

import lotto.domain.Lotto;

import java.util.List;

public class ManualLottoGenerator {
    public static Lotto generateLotto(String userInput) {
        return generateLotto(LottoParser.getNumbers(userInput));
    }

    public static Lotto generateLotto(List<Integer> userInput) {
        return new Lotto(userInput);
    }
}
