package lotto.model;

public class LottoCountManual extends LottoCount {
    public LottoCountManual(Cash cash) {
        this((int)(cash.getCash() / LottoConstants.LOTTO_PRICE));
    }

    public LottoCountManual(int number) {
        super(number);
    }
}
