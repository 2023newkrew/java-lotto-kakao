package domain.buyer;

public class Budget {
    private final int budget;

    public Budget(int budget) {
        if (budget < 0) throw new IllegalArgumentException("budget은 양수여야 합니다");

        this.budget = budget;
    }

    public int getAbleLotteryCount(int price) {
        if (price <= 0) throw new IllegalArgumentException("가격은 양수여야 합니다");

        return budget / price;
    }
}
