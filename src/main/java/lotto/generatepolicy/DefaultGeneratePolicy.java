package lotto.generatepolicy;

import static lotto.domain.LottoConstants.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.domain.LottoConstants.LOTTO_NUMBER_UPPER_BOUND;

import java.util.Random;

public class DefaultGeneratePolicy implements GeneratePolicy {

    private static final Random random = new Random();

    public int generate() {
        return LOTTO_NUMBER_LOWER_BOUND.getValue() + random.nextInt(
                LOTTO_NUMBER_UPPER_BOUND.getValue() - LOTTO_NUMBER_LOWER_BOUND.getValue() + 1);
    }
}
