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

    public void purchaseLotto(Player player) {
        long ticketCount = player.getOwnMoney() / lottoTicketPrice;
        player.takeMoney(ticketCount * lottoTicketPrice);
        player.giveTickets(Stream.generate(LottoTicket::fromRandom)
                .limit(ticketCount)
                .collect(Collectors.toList()));
    }

    public void purchaseLotto(Player player, List<LottoTicket> manualTickets) {
        long totalPrice = manualTickets.size() * lottoTicketPrice;
        player.takeMoney(totalPrice);
        player.giveTickets(manualTickets);
    }

}
