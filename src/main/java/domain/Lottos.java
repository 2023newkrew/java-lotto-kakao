package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public void addManualLotto(String input) {
        lottos.add(Lotto.getManualLotto(input));
    }

    public void addAutoLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.getAutoLotto());
        }
    }

    public int getSize() {
        return lottos.size();
    }

    public TotalResult getTotalResult(WinningLottoWithBonus winningLottoWithBonus) {
        TotalResult totalResult = new TotalResult();
        lottos.stream()
                .forEach(lotto -> totalResult.increaseValueOfResult(lotto.getResult(winningLottoWithBonus)));
        return totalResult;
    }

    public List<String> getPurchasedLottosNumbers() {
        return lottos.stream()
                .map(Lotto::lottoToString)
                .collect(Collectors.toList());
    }
}
