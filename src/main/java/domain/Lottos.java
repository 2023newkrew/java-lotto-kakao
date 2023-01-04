package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.getAutoLotto());
        }
    }

    public void add(String input) {
        lottos.add(Lotto.getManualLotto(input));
    }

    public int getSize() {
        return lottos.size();
    }

    public TotalResult getTotalResult(WinningLotto winningLotto, LottoNumber bonusNumber) {
        TotalResult totalResult = new TotalResult();
        lottos.stream()
                .forEach(lotto -> totalResult.increaseValueOfResult(lotto.getResult(winningLotto, bonusNumber)));
        return totalResult;
    }

    public List<String> getPurchasedLottosNumbers() {
        return lottos.stream()
                .map(Lotto::getLottoNumbers)
                .collect(Collectors.toList());
    }
}
