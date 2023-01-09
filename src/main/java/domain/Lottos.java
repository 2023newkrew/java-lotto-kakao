package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public void addManualLotto(Lotto lotto) {
        lottos.add(lotto);
    }

    public void addAutoLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.getLotto(new AutoLottoGenerator()));
        }
    }

    public TotalResult getTotalResult(WinningLotto winningLotto) {
        TotalResult totalResult = new TotalResult();
        lottos.stream()
                .forEach(lotto -> totalResult.increaseValueOfResult(winningLotto.getResult(lotto)));
        return totalResult;
    }

    public List<String> getPurchasedLottosNumbers() {
        return lottos.stream()
                .map(Lotto::lottoToString)
                .collect(Collectors.toList());
    }

    public boolean containsLotto(Lotto lotto) {
        return lottos.contains(lotto);
    }

    public int getSize() {
        return lottos.size();
    }

}
