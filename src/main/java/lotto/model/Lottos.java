package lotto.model;

import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Lotto> lottos;

    public Lottos(int lottoCount) {
        this(new ArrayList<>(), lottoCount);
    }

    public Lottos(List<List<Integer>> manualLottoNumbers, int autoLottoCount) {
        RandomGenerator randomGenerator = new RandomGenerator();

        lottos = new ArrayList<>();
        manualLottoNumbers.forEach(v -> lottos.add(new Lotto(v)));
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(randomGenerator.createNumbers(LOTTO_NUMBER_SIZE)));
        }
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void saveResult(WinningLotto winningLotto, PriceResult priceResult) {
        for (Lotto lotto : lottos) {
            priceResult.addResult(winningLotto.getPrice(lotto));
        }
    }

    public int size() {
        return lottos.size();
    }
}
