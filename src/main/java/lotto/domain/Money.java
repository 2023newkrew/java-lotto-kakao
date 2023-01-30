package lotto.domain;

public class Money {

    private final int money;

    public Money(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("돈의 금액은 양수여야 합니다.");
        }
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
