package lotto.model;

import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Lotto> lottos;

    public Lottos(int count) {
        RandomGenerator randomGenerator = new RandomGenerator();

        lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(randomGenerator.createNumbers(LOTTO_NUMBER_SIZE)));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
