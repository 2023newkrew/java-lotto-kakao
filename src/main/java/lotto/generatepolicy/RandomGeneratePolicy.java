package lotto.generatepolicy;

import java.util.Random;
import lotto.domain.LottoConstants;

public class RandomGeneratePolicy implements GeneratePolicy {

    private static final Random random = new Random();

    public RandomGeneratePolicy() {
    }

    @Override
    public int generate() {
        return random.nextInt(LottoConstants.MAX_LOTTO_NUMBER) + LottoConstants.MIN_LOTTO_NUMBER;
    }

}
