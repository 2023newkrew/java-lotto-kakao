package domain.lotto;

public class LottoTicketPurchaseAmount {
    public static final int MINIMUM_PURCHASE_AMOUNT = 0;
    private final int purchaseAmount;

    public LottoTicketPurchaseAmount(int purchaseAmount, Cost cost) {
        validate(purchaseAmount, cost);
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public boolean isZero() {
        return purchaseAmount == 0;
    }

    private void validate(int purchaseAmount, Cost cost) {
        if (purchaseAmount < MINIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException("로또 구매 개수는 0 이상이어야 합니다.");
        }
        if (cost.countAvailablePurchases(LottoShop.LOTTO_PRICE) < purchaseAmount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }
}
