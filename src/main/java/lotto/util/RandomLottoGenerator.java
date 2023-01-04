package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoGenerator {
    public Lotto generateLotto() {
        return new Lotto(
                RandomNumberGenerator.getRandomNumbers(
                        Lotto.FIXED_SIZE,
                        LottoNumber.MINIMUM_BOUNDARY,
                        LottoNumber.MAXIMUM_BOUNDARY
                ).stream().map(LottoNumber::new).collect(Collectors.toList())
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
