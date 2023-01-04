package lotto.domain;

public class Store {

    public static final int LOTTO_PRICE = 1000;
    private final Money money;

    public Store(Money money) {
        if (money.getMoney() < LOTTO_PRICE) {
            throw new IllegalArgumentException("해당 돈으로 로또를 구매할 수 없습니다.");
        }
        this.money = money;
    }

    public int getLottoAmount() {
        return money.getMoney() / LOTTO_PRICE;
    }
}
