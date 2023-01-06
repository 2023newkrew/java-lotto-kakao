package domain;

import utils.LottoCalculator;

import static constant.ExceptionMessage.INVALID_NUMBER_OF_LOTTO_BUY_MANUALLY;

public class LottoCount {

    private int count;

    public static LottoCount of(Payment payment, int count) {
        validateLottoCount(payment, count);
        return new LottoCount(count);
    }

    private LottoCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    private static void validateLottoCount(Payment payment, int count) {
        if (count > LottoCalculator.calculateLottoCount(payment)) {
            throw new RuntimeException(INVALID_NUMBER_OF_LOTTO_BUY_MANUALLY);
        }
    }

}
