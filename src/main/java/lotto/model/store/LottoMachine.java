package lotto.model.store;

import lotto.model.ticket.LottoNumber;
import lotto.model.ticket.LottoTicket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LottoMachine {

    private final Money price;

    public static LottoMachine create(Money price) {
        if (Money.isNullOrZero(price)) {
            throw new IllegalArgumentException("로또 가격은 0원일 수 없습니다.");
        }

        return new LottoMachine(price);
    }

    public LottoMachine(Money price) {
        this.price = price;
    }


    public long getPurchasableCount(Money money) {
        if (Objects.isNull(money)) {
            return 0L;
        }

        return money.divide(price).longValue();
    }

    public LottoTicket createRandomTicket(long count) {
        validateCount(count);
        List<LottoNumber> lottos = LongStream.range(0, count)
                .mapToObj(ignore -> LottoNumber.createByRandom())
                .collect(Collectors.toList());

        return LottoTicket.of(lottos);
    }

    private static void validateCount(long count) {
        if(count < 0){
            throw new IllegalArgumentException("생성할 로또의 수량이 0보다 작습니다.");
        }
    }

    public LottoReceipt createReceipt(Money money, long count) {
        validateCount(count);
        Money totalPrice = price.multiply(BigDecimal.valueOf(count));

        return LottoReceipt.from(money, totalPrice);
    }
}
