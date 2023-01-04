package lotto.model;

import lotto.model.enums.RankingType;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }


    public List<Lotto> getLottos() {
        return lottos;
    }

    public List<RankingType> getRankings(List<Integer> winNumbers, int bonus) {
        List<RankingType> rankingTypes = new ArrayList<>();
        for(Lotto lotto: lottos){
            rankingTypes.add(lotto.checkWin(winNumbers, bonus));
        }
        return rankingTypes;
    }
}
