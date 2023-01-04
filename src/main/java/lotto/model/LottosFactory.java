package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottosFactory {
    public Lottos makeLottosAuto(int numberOfLotto){
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(new Lotto());
        }
        return new Lottos(lottos);
    }

    public Lottos makeLottosManual(List<List<Integer>> manualLottosNumbers){
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> manualLottoNumbers : manualLottosNumbers) {
            lottos.add(new Lotto(manualLottoNumbers));
        }
        return new Lottos(lottos);
    }
}
