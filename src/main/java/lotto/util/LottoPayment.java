package lotto.util;

public class LottoPayment {
    public static final int LOTTO_COST = 1000;

    public static int getAmount(int purchase) {
        return Math.max(0, purchase / LOTTO_COST);
    }
}
