package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

import java.util.List;

public class LottoConverter {

    public Lotto toLotto(List<Integer> numList) {
        return new Lotto(numList);
    }

    public Lottos toLottos(List<List<Integer>> numList2D) {
        Lottos lottos = new Lottos();
        for (List<Integer> numList : numList2D) {
            lottos.add(this.toLotto(numList));
        }
        return lottos;
    }

    public WinningLotto toWinningLotto(List<Integer> numList, Integer bonusNumber) {
        return new WinningLotto(numList, bonusNumber);
    }
}
