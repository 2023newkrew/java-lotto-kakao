package lotto.model;

public class Exchange {
    public static final long LOTTO_PRICE = 1000L;

    public static boolean isEnough(long cash) {
        return cash >= Exchange.LOTTO_PRICE;
    }

    public Lotto purchaseLotto(long cash) {
        Lotto lotto = new Lotto();
        lotto.purchaseRandomLotto(cash / Exchange.LOTTO_PRICE);
        return lotto;
    }

    public long getPrizeAmount(PrizeRecord prizeRecord) {
        long cash = 0;
        for (Prize prize : Prize.values()) {
            cash += prizeRecord.getCountOf(prize) * prize.prize();
        }
        return cash;
    }

    private long getPurchaseAmount(PrizeRecord prizeRecord) {
        int quantity = 0;
        for (Prize prize : Prize.values()) {
            quantity += prizeRecord.getCountOf(prize);
        }
        return Exchange.LOTTO_PRICE * quantity;
    }

    public double calculateYield(PrizeRecord prizeRecord) {
        return (double) this.getPrizeAmount(prizeRecord) / this.getPurchaseAmount(prizeRecord);
    }
}
