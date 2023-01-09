package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(Lottos manualLottos, Lottos autoLottos) {
        lottos = manualLottos.concat(autoLottos);
    }

    private List<Lotto> concat(Lottos other) {
        return Stream.concat(lottos.stream(), other.lottos.stream()).collect(Collectors.toList());
    }

    public static Lottos getAutoLottos(int count) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            autoLottos.add(LottoFactory.getLotto(AutoLottoGenerator.get()));
        }
        return new Lottos(autoLottos);
    }

    public TotalResult getTotalResult(WinningLotto winningLotto) {
        TotalResult totalResult = new TotalResult();
        lottos.forEach(lotto -> totalResult.increaseValueOfResult(winningLotto.getResult(lotto)));
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
