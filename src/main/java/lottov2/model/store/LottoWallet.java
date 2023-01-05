package lottov2.model.store;

import lottov2.model.ticket.LottoTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class LottoWallet {

    private final Money baseMoney;

    private Money balance;

    private LottoTicket ticket = LottoTicket.of();

    public static LottoWallet create(Money baseMoney) {
        if (Money.isNullOrZero(baseMoney)) {
            throw new IllegalArgumentException("투자금은 0원일 수 없습니다.");
        }

        return new LottoWallet(baseMoney);
    }

    private LottoWallet(Money baseMoney) {
        this.baseMoney = baseMoney;
        this.balance = baseMoney;
    }

    public void buyLottoTicketAutomatically(LottoStore lottoStore) {
        long lottoCount = lottoStore.getPurchasableCount(balance);
        LottoTicket boughtTicket = lottoStore.buyAutomatically(lottoCount);
        Money totalPrice = lottoStore.getTotalPrice(lottoCount);
        ticket = ticket.append(boughtTicket);
        balance = balance.subtract(totalPrice);
    }

    public LottoTicket getTicket() {
        return ticket;
    }

    public BigDecimal getProfitRate(Money totalPrize) {
        return totalPrize.add(balance).divide(baseMoney, 2, RoundingMode.DOWN);
    }
}
