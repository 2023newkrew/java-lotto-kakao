package lotto.factory;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosFactory extends LottosFactory{
    @Override
    public Lottos generate(int numberOfLotto) {
        List<List<Integer>> manualLottosNumbers = new InputView().receiveManualLottos(numberOfLotto);
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> manualLottoNumbers : manualLottosNumbers) {
            lottos.add(new Lotto(manualLottoNumbers));
        }
        return new Lottos(lottos);
    }
}
