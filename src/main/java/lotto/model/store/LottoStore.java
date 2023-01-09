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
        LottoTicket ticket = lottoMachine.createRandomTicket(lottoCount);
        LottoReceipt receipt = lottoMachine.createReceipt(money, lottoCount);

        return PurchaseResult.of(ticket, receipt);
    }

    public long getPurchasableCount(Money money) {
        return lottoMachine.getPurchasableCount(money);
    }

    public PurchaseResult buyManually(Money money, LottoTicket ticket) {
        long lottoCount = getPurchasableCount(money);
        LottoTicket randomTicket = lottoMachine.createRandomTicket(lottoCount - ticket.count());
        LottoReceipt receipt = lottoMachine.createReceipt(money, lottoCount);

        return PurchaseResult.of(ticket.append(randomTicket), receipt);
    }
}
