package lotto.model;

public class Exchange {
    public static final long LOTTO_PRICE = 1000L;

    public static boolean isEnough(long cash) {
        return cash >= Exchange.LOTTO_PRICE;
    }

    public Lotto cashToLotto(long cash) {
        Lotto lotto = new Lotto();
        lotto.purchaseRandomLotto(cash / Exchange.LOTTO_PRICE);
        return lotto;
    }

    public long prizeToCash(PrizeRecord prizeRecord) {
        long cash = 0;
        for (Prize prize : Prize.values()) {
            cash += prizeRecord.getCountOf(prize) * prize.prize();
        }
        return cash;
    }
}
