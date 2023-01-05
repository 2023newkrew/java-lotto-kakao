package lotto.ui.model;

import lotto.core.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoShop {
    private static final long DEFAULT_LOTTO_PRICE = 1000;
    private final long lottoTicketPrice;

    public LottoShop() {
        this(DEFAULT_LOTTO_PRICE);
    }

    public LottoShop(long lottoTicketPrice) {
        this.lottoTicketPrice = lottoTicketPrice;
    }

    public List<LottoTicket> purchase(long purchasePrice) {
        if (purchasePrice < 0) {
            throw new RuntimeException("구입 금액은 0 보다 커야 합니다.");
        }
        if (purchasePrice % lottoTicketPrice != 0) {
            throw new RuntimeException(String.format("로또 가격은 %d원입니다 구매에 거스름돈이 남으면 안됩니다.", DEFAULT_LOTTO_PRICE));
        }
        return Stream.generate(LottoTicket::fromRandom)
                .limit(purchasePrice / lottoTicketPrice)
                .collect(Collectors.toList());
    }
}
