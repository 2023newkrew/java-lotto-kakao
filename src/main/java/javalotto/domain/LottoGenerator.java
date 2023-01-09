package javalotto.domain;

import javalotto.util.LottoConstants;

import java.util.ArrayList;
import java.util.List;

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
            lottos.add(getLotto());
        }

        return Lottos.from(lottos);
    }

    private Lotto getLotto() {
        return Lotto.from(numberGenerator.generateNumbers(LottoConstants.LOTTO_NUMBER_MIN_VALUE, LottoConstants.LOTTO_NUMBER_MAX_VALUE + 1, LottoConstants.LOTTO_NUMBERS_COUNT));
    }
}
