package lotto.model;

import java.util.ArrayList;
import java.util.List;

public abstract class LottosFactory {

    public abstract Lottos generate(int numberOfLotto);

    public Lottos joinLottos(Lottos manualLottos, Lottos autoLottos) {
        List<Lotto> joinLottos = new ArrayList<>();
        joinLottos.addAll(manualLottos.getLottos());
        joinLottos.addAll(autoLottos.getLottos());

        return new Lottos(joinLottos);
    }
}
