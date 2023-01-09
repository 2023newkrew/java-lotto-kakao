package lotto.model.store;

import lotto.model.ticket.LottoTicket;

import java.util.Objects;

public class LottoStore {

    private final Money price;

    public static LottoStore create(Money price) {
        if (Money.isNullOrZero(price)) {
            throw new IllegalArgumentException("로또 가격은 0원일 수 없습니다.");
        }

        return new LottoStore(price);
    }

    public LottoStore(Money price) {
        this.price = price;
    }

    public long getPurchasableCount(Money money) {
        if (Objects.isNull(money)) {
            return 0L;
        }

        return (long) money.divide(price);
    }

    public PurchaseResult buyAutomatically(Money money) {
        long lottoCount = getPurchasableCount(money);
        LottoTicket ticket = LottoTicket.createByRandom(lottoCount);
        LottoReceipt receipt = createReceipt(money, lottoCount);

        return PurchaseResult.of(ticket, receipt);
    }

    public PurchaseResult buyManually(Money money, LottoTicket ticket) {
        long lottoCount = getPurchasableCount(money);
        LottoTicket randomTicket = LottoTicket.createByRandom(lottoCount - ticket.count());
        LottoReceipt receipt = createReceipt(money, lottoCount);

        return PurchaseResult.of(ticket.append(randomTicket), receipt);
    }

    private LottoReceipt createReceipt(Money money, long count) {
        Money totalPrice = price.multiply(count);

        return LottoReceipt.from(money, totalPrice);
    }
}
