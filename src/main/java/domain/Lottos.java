package domain;

import common.constant.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();

    public Lottos() {
    }

    public Lottos(int paidPrice) {
        int count = paidPrice / Constants.PRICE;
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }
    }

    public void add(String input) {
        lottos.add(new Lotto(input));
    }


    public int getSize() {
        return lottos.size();
    }

    public TotalResult getTotalResult(WinningLotto winningLotto, BonusNumber bonusNumber) {
        TotalResult totalResult = new TotalResult();
        lottos.stream()
                .forEach(lotto -> totalResult.put(lotto.getResult(winningLotto, bonusNumber)));

        return totalResult;
    }
}
