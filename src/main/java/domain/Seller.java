package domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private static final int SINGLE_LOTTO_TICKET_PRICE = 1000;

    private final LottoNumberPicker lottoNumberPicker;

    public Seller() {
        this.lottoNumberPicker = new LottoNumberPicker();
    }

    public List<LottoTicket> sellLottoTickets(int payMoney) {
        validatePayMoney(payMoney);
        int lottoTicketCount = payMoney / SINGLE_LOTTO_TICKET_PRICE;
        List<LottoTicket> lottoTicketBought = new ArrayList<>();
        for (int i = 0; i < lottoTicketCount; i++) {
            lottoTicketBought.add(lottoNumberPicker.makeLottoTicket());
        }
        return lottoTicketBought;
    }

    private void validatePayMoney(int payMoney) {
        if (payMoney < SINGLE_LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("최소 1,000원 이상을 지불해야 합니다.");
        }
    }
}
