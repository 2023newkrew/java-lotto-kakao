package lotto.model;

import lotto.model.enums.RankingType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public List<RankingType> getRankings(WinningNumbers winningNumbers) {
        List<RankingType> rankingTypes = new ArrayList<>();
        for (Lotto lotto : lottos) {
            rankingTypes.add(lotto.checkWin(winningNumbers));
        }
        return rankingTypes;
    }
}
