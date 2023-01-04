package lotto.util;

public class LottoPayment {
    public static final int LOTTO_COST = 1000;

    public static int getAmount(int purchase) {
        if (purchase < 1000) {
            throw new RuntimeException("로또를 구매하기 위한 금액이 부족합니다.");
        }
        return Math.max(0, purchase / LOTTO_COST);
    }
}
