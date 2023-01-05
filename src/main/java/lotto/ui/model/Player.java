package lotto.ui.model;

import lotto.core.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final ArrayList<LottoTicket> lottoTickets = new ArrayList<>();
    private long totalPurchase = 0;

    public List<LottoTicket> getLottoTickets() {
        return List.copyOf(lottoTickets);
    }

    public void purchaseTicket(LottoShop shop, long price) {
        lottoTickets.addAll(shop.purchase(price));
        totalPurchase += price;
    }

    public long getTotalPurchase() {
        return totalPurchase;
    }
}
