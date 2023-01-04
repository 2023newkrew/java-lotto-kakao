package lotto.domain;

public class LottoSeller {
    public final static int LOTTO_PRICE = 1000;

    public static int getLottoAmount(Money money) {
        if (money.money() < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 한 장의 가격보다 커야 합니다.");
        }
        return money.money() / LOTTO_PRICE;
    }

}
