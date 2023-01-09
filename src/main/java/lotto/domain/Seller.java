package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Seller {

    private static final Money SINGLE_LOTTO_TICKET_PRICE = new Money(1000);
    private static final String MINIMUM_MONEY_ERROR_MESSAGE = "최소 1,000원 이상을 지불해야 합니다.";
    private static final String NOT_ENOUGH_MONEY_ERROR_MESSAGE = "돈이 모자라 구매할 수 없습니다.";

    private final LottoNumberPicker lottoNumberPicker;

    public Seller() {
        this.lottoNumberPicker = new LottoNumberPicker();
    }

    public void checkHasEnoughMoneyForManualLottoTickets(int manualLottoTicketsCount, Money money) {
        Money requiredMoney = SINGLE_LOTTO_TICKET_PRICE.multiply(manualLottoTicketsCount);
        if (money.isSmallerThan(requiredMoney)) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_ERROR_MESSAGE);
        }
    }

    public List<LottoTicket> sellAutoLottoTickets(Money payMoney) {
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
            throw new IllegalArgumentException(MINIMUM_MONEY_ERROR_MESSAGE);
        }
    }

    public Money calculateTotalPrice(List<LottoTicket> lottoTickets) {
        return SINGLE_LOTTO_TICKET_PRICE.multiply(lottoTickets.size());
    }
}
