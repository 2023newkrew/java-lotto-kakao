package javalotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javalotto.constants.LottoConstants.*;

public class LottoGenerator {

    private final NumberGenerator numberGenerator;

    private LottoGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public static LottoGenerator from(NumberGenerator numberGenerator) {
        return new LottoGenerator(numberGenerator);
    }

    public Lottos generateLottos(TotalLottoCount lottoCount) {
        List<Lotto> lottos = Stream.generate(this::generateLotto)
                .limit(lottoCount.getTotalCount())
                .collect(Collectors.toList());

        return Lottos.from(lottos);
    }

    private Lotto generateLotto() {
        return Lotto.from(numberGenerator.generateNumbers(
                LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE + 1, LOTTO_NUMBERS_COUNT));
    }
}
