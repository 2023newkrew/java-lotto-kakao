package lotto.domain;

public class LottoPayment {
    public static final int LOTTO_COST = 1000;

    private int wallet;

    public int getAmount() {
        return Math.max(0, wallet / LOTTO_COST);
    }

    public LottoPayment(int purchase) {
        if (wallet < 0) {
            throw new IllegalArgumentException("금액은 0이상 이어야 합니다.");
        }
        wallet = purchase;
    }

    public void buyLotto(int amount) {
        if(amount < 0)
            throw new IllegalArgumentException("수량은 양의 정수만 가능합니다");
        if(amount * LOTTO_COST > wallet)
            throw new RuntimeException("로또를 구매하기 위한 돈이 부족합니다.");
        wallet -= amount * LOTTO_COST;
    }

    public int getWallet() {
        return wallet;
    }
}
