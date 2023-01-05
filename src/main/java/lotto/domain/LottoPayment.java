package lotto.domain;

public class LottoPayment {
    public static final int LOTTO_COST = 1000;

    private int wallet;

    public static int getAmount(int purchase) {
        if (purchase < 1000) {
            throw new RuntimeException("로또를 구매하기 위한 금액이 부족합니다.");
        }
        return Math.max(0, purchase / LOTTO_COST);
    }

    public LottoPayment(int purchase) {
        wallet = purchase;
    }

    public void buyLotto(int amount) {
        if(amount * LOTTO_COST > wallet)
            throw new RuntimeException("돈이 부족합니다.");
        wallet -= amount * LOTTO_COST;
    }

    public int getWallet() {
        return wallet;
    }
}
