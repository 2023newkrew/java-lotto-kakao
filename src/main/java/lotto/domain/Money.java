package lotto.domain;

public class Money {
    private final int money;

    public Money(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("돈은 1000원 단위여야 합니다.");
        }

        if (money <= 0) {
            throw new IllegalArgumentException("돈은 양수여야 합니다.");
        }

        this.money = money;
    }

    public int getLottoAmount() {
        return this.money / 1000;
    }
}
