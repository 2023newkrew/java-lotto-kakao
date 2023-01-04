package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;

public class RandomLottoGenerator {
    public Lotto generateLotto() {
        return new Lotto(
                RandomNumberGenerator.getRandomNumbers(
                        Lotto.FIXED_SIZE,
                        LottoNumber.MIN_NUMBER,
                        LottoNumber.MAX_NUMBER
                )
        );
    }

    public Lottos generateLottos(int amount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottoList.add(generateLotto());
        }
        return new Lottos(lottoList);
    }
}
