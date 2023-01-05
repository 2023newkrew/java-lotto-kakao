package lotto.util;

import lotto.config.LottoConfig;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class RandomLottoGenerator {
    public Lotto generateLotto() {
        return new Lotto(
                RandomNumberGenerator.get(
                        LottoConfig.LOTTO_SIZE,
                        LottoConfig.MIN_NUMBER,
                        LottoConfig.MAX_NUMBER
                )
        );
    }

    public Lottos generateLottos(int amount) {
        Lottos lottos = new Lottos();
        for (int i = 0; i < amount; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }
}
