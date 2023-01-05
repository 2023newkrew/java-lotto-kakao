package lottov2.model.store;

import lottov2.model.ticket.LottoNumber;
import lottov2.model.ticket.LottoTicket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LottoStore {

    private final Money price;

    public static LottoStore create(Money price) {
        if (isNullOrZero(price)) {
            throw new IllegalArgumentException("로또 가격은 0원일 수 없습니다.");
        }

        return new LottoStore(price);
    }

    public static boolean isNullOrZero(Money price) {
        return Objects.isNull(price) || price.equals(Money.ZERO);
    }

    public LottoStore(Money price) {
        this.price = price;
    }

    public long getPurchasableCount(Money money) {
        return money.divide(price).longValue();
    }

    public Money getTotalPrice(long count) {
        return price.multiply(BigDecimal.valueOf(count));
    }

    public LottoTicket buyAutomatically(long count) {
        List<LottoNumber> lottos = LongStream.range(0, count)
                .mapToObj(ignore -> LottoNumber.createByRandom())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }
}
