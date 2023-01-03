package lotto.model;

import lotto.model.lotto.LottoBundle;
import lotto.model.vo.Money;

public class PurchaseResult {

    private final LottoBundle lottoBundle;

    private final Money totalPrice;

    public static PurchaseResult from(LottoBundle lottoBundle, Money totalPrice) {
        return new PurchaseResult(lottoBundle, totalPrice);
    }

    public PurchaseResult(LottoBundle lottoBundle, Money totalPrice) {
        this.lottoBundle = lottoBundle;
        this.totalPrice = totalPrice;
    }

    public LottoBundle getLottoBundle() {
        return lottoBundle;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }
}