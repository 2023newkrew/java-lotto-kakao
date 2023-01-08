package lotto.model;

public class LottoCountAuto extends LottoCount {
    public LottoCountAuto(Cash cash) {
        this((int)(cash.getCash() / LottoConstants.LOTTO_PRICE));
    }

    public LottoCountAuto(int number) { super(number); }
}
