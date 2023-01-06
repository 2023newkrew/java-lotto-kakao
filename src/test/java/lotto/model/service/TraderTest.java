package lotto.model.service;

import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TraderTest {
    @Test
    @DisplayName("can purchase lotto")
    void can_purchase_lotto() {
        Trader trader = new Trader(LottoTicket.PRICE * 10);
        LottoPublisher lottoPublisher = new LottoPublisher();

        LottoTickets tickets = lottoPublisher.publishRandomLotto(4);
        trader.purchaseLotto(tickets);
        assertEquals(6, trader.getFullPurchaseQuantity());
        assertEquals(tickets.toString(), trader.getPurchasedTickets().toString());
    }
}
