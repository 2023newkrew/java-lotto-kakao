package lotto.model;

public class Money {
    public static final int LOTTO_PRICE = 1000;
    private final int leftOver;
    private final int used;

    public Money(int initial) {
        validateInitialMoney(initial);
        this.leftOver = initial;
        this.used = 0;
    }

    private Money(int leftOver, int used) {
        this.leftOver = leftOver;
        this.used = used;
    }

    private void validateInitialMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액은 0 보다 큰 정수여야 합니다");
        }
    }

    public Money buyLottoTicketsAsManyAs(int count) {
        validateManualTicketCount(count);
        int used = LOTTO_PRICE * count;
        return new Money(leftOver - used, this.used + used);
    }

    private void validateManualTicketCount(int count) {
        if (getPurchasableCount() < count) {
            throw new IllegalArgumentException("가지고 있는 금액으로 구매할 수 있는 개수보다 작거나 같은 값을 입력해주세요");
        }
    }

    public Money buyLottoTicketsConsumingAllLeftOver() {
        int used = LOTTO_PRICE * getPurchasableCount();
        return new Money(leftOver - used, this.used + used);
    }

    public int getPurchasableCount() {
        return leftOver / LOTTO_PRICE;
    }

    public int getUsedMoney() {
        return used;
    }
}
