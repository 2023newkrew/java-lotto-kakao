package lotto.util;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator {
    public static Lotto generateLotto(String userInput) {
        return generateLotto(LottoParser.getNumbers(userInput));
    }

    public static Lotto generateLotto(List<Integer> userInput) {
        return new Lotto(userInput);
    }

    public static List<Lotto> generateLottos(List<String> userLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (String userLotto : userLottos) {
            lottos.add(generateLotto(userLotto));
        }
        return lottos;
    }
}
