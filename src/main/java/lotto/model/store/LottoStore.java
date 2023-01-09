package lotto.model.store;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.util.List;
import java.util.Objects;

public class LottoStore {

    private final LottoMachine lottoMachine;

    public static LottoStore create(LottoMachine lottoMachine) {
        if (Objects.isNull(lottoMachine)) {
            throw new IllegalArgumentException("로또 머신이 없습니다.");
        }

        return new LottoStore(lottoMachine);
    }

    public LottoStore(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public PurchaseResult buyAutomatically(Money money) {
        long lottoCount = getPurchasableCount(money);
        LottoTicket ticket = createRandomLottos(lottoCount);
        Money totalPrice = getTotalPrice(lottoCount);
        LottoReceipt receipt = LottoReceipt.from(money, totalPrice);

        return PurchaseResult.of(ticket, receipt);
    }

    private long getPurchasableCount(Money money) {
        if (Objects.isNull(money)) {
            return 0L;
        }

        return money.divide(price).longValue();
    }

    private Money getTotalPrice(long count) {
        return price.multiply(BigDecimal.valueOf(count));
    }

    private LottoTicket createRandomLottos(long count) {
        List<LottoNumber> lottos = LongStream.range(0, count)
                .mapToObj(ignore -> LottoNumber.createByRandom())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }
}
