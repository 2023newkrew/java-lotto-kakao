package javalotto.domain;

import java.util.ArrayList;
import java.util.List;

import static javalotto.domain.Lotto.LOTTO_NUMBER_MAX_VALUE;
import static javalotto.domain.Lotto.LOTTO_NUMBER_MIN_VALUE;

public class LottoGenerator {

    private final NumberGenerator numberGenerator;

    private LottoGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public static LottoGenerator from(NumberGenerator numberGenerator) {
        return new LottoGenerator(numberGenerator);
    }

    public Lottos getLottos(LottoCount lottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount.getCount(); i++) {
            lottos.add(getLotto(lottoCount));
        }

        return Lottos.from(lottos);
    }

    private Lotto getLotto(LottoCount lottoCount) {
        return Lotto.from(numberGenerator.generateNumbers(LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE + 1, lottoCount.getCount()));
    }
}
