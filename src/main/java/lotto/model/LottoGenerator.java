package lotto.model;

import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    private List<Lotto> lottos;

    public void createLottos(int count) {
        RandomGenerator randomGenerator = new RandomGenerator();

        lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(randomGenerator.createNumbers(6)));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
