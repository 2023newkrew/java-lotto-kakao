package lotto.model;

import lotto.model.enums.RankingType;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    List<Lotto> lottos = new ArrayList<>();

    public Lottos(int input) {
        addRandom(input);
    }

    private void addRandom(int input) {
        for (int i = 0; i < input; i++) {
            lottos.add(new Lotto());
        }
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
