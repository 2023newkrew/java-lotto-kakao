package javalotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javalotto.constants.LottoConstants.*;

public class LottoShop {

    private final NumberGenerator numberGenerator;

    private LottoShop(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public static LottoShop from(NumberGenerator numberGenerator) {
        return new LottoShop(numberGenerator);
    }

    public Lottos issueAutoLottos(int count) {
        List<Lotto> lottos = Stream.generate(this::issueLotto)
                .limit(count)
                .collect(Collectors.toList());

        return Lottos.from(lottos);
    }

    public Lottos issueManualLottos(List<List<Integer>> manualLottoNumbers) {
        return Lottos.fromNumbers(manualLottoNumbers);
    }

    private Lotto issueLotto() {
        return Lotto.from(numberGenerator.generateNumbers(
                LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE + 1, LOTTO_NUMBERS_COUNT));
    }
}
