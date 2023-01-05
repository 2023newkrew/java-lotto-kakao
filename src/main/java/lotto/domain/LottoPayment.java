package lotto.domain;

public class LottoPayment {
    public static final int LOTTO_COST = 1000;

    private int wallet;

    public int getAmount() {
        return Math.max(0, wallet / LOTTO_COST);
    }

    public LottoPayment(int purchase) {
        if (wallet < 0) {
            throw new RuntimeException("로또를 구매하기 위한 금액이 부족합니다.");
        }
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
