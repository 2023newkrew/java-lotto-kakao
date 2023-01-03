package domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private static final Money SINGLE_LOTTO_TICKET_PRICE = new Money(1000);

    private final LottoNumberPicker lottoNumberPicker;

    public Seller() {
        this.lottoNumberPicker = new LottoNumberPicker();
    }

    public List<LottoTicket> sellLottoTickets(Money payMoney) {
        validatePayMoney(payMoney);
        int lottoTicketCount = (int) payMoney.divideBy(SINGLE_LOTTO_TICKET_PRICE);
        List<LottoTicket> lottoTicketBought = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTicketBought.add(lottoNumberPicker.makeLottoTicket());
        }
        return lottoTicketBought;
    }

    private void validatePayMoney(Money payMoney) {
        if (payMoney.isSmallerThan(SINGLE_LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException("최소 1,000원 이상을 지불해야 합니다.");
        }
    }
}
