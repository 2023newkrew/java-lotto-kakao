package lottov2.model.store;

import lottov2.model.ticket.LottoTicket;
import lottov2.model.ticket.LottoWallet;
import lottov2.model.wallet.Money;

public class LottoStore {

    public long getPurchasableCount(Money money){
        return 0;
    }

    public Money getTotalPrice(long count){
        return Money.valueOf(count);
    }

    public LottoTicket buyAutomatically(long count){
        return null;
    }
}
