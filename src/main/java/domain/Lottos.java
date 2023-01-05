package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> purchasedLottos = new ArrayList<>();

    public Lottos() {
    }

    public void addLottoAutomatically(int count){
        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto();
            purchasedLottos.add(lotto);
        }
    }

    public void addLottoManually(Lotto lotto){
        purchasedLottos.add(lotto);
    }

    public void add(String input) {
        purchasedLottos.add(new Lotto(input));
    }
    public int getSize() {
        return purchasedLottos.size();
    }

    public TotalResult getTotalResult(WinningLotto winningLotto, BonusNumber bonusNumber) {
        TotalResult totalResult = new TotalResult();
        purchasedLottos.stream()
                .forEach(lotto -> totalResult.increaseValueOfResult(lotto.getResult(winningLotto, bonusNumber)));
        return totalResult;
    }

    public List<String> getPurchasedLottosNumbers() {
        return purchasedLottos.stream()
                .map(Lotto::getLottoNumbers)
                .collect(Collectors.toList());
    }
}
