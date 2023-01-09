package domain;

public class LottoStore {
    public static final Integer LOTTO_COST = 1000;
    private static final String ERROR_MONEY_LOWER_THAN_COST = "로또를 구매하기 위한 금액이 부족합니다.";
    private static final String ERROR_PURCHASE_NOTHING = "구매 금액이 로또 가격보다 작아 구매할 수 없습니다.";

    public void validatePurchase(Integer money, Integer amount) {
        if (money < LOTTO_COST) {
            throw new IllegalArgumentException(ERROR_PURCHASE_NOTHING);
        }
        if ((money - (amount * LOTTO_COST)) < 0) {
            throw new IllegalArgumentException(ERROR_MONEY_LOWER_THAN_COST);
        }
    }

    public Integer getPurchaseAmount(Integer money) {
        return money / LOTTO_COST;
    }
}
