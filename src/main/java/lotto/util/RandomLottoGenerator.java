package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.List;

public class RandomLottoGenerator {
    public static Lotto generateLotto() {
        return new Lotto(RandomNumberGenerator.getRandomNumbers(
                LottoNumbers.SIZE,
                LottoNumber.MINIMUM_BOUNDARY,
                LottoNumber.MAXIMUM_BOUNDARY
        ));
    }

    public static List<Lotto> generateLottos(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }
}
