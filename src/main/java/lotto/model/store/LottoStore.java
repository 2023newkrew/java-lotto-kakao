package lotto.model.store;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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
