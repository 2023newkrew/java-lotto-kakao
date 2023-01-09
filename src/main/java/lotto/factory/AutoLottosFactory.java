package lotto.factory;

import lotto.model.Lotto;
import lotto.model.Lottos;

import java.util.ArrayList;
import java.util.List;

public class AutoLottosFactory extends LottosFactory{
    @Override
    public Lottos generate(int numberOfLotto) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLotto; i++) {
            lottos.add(new Lotto());
        }
        return new Lottos(lottos);
    }
}
