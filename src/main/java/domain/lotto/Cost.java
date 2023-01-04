package domain.lotto;

public class Cost {

    public static final int MINIMUM_VALUE = 0;

    private final int value;

    public Cost(int value) {
        validateRange(value);
        this.value = value;
    }

    public int countAvailablePurchases(int price) {
        return value / price;
    }

    private void validateRange(int value) {
        if (value < MINIMUM_VALUE) {
            throw new IllegalArgumentException("잘못된 범위의 숫자입니다.");
        }
    }
}
