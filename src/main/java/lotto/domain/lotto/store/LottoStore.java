package lotto.domain.lotto.store;

import lotto.domain.UserAccount;
import lotto.domain.lotto.ticket.LottoTicket;
import lotto.domain.lotto.ticket.generator.LottoTicketGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public List<LottoTicket> buyLottoTicket(UserAccount userAccount, int buyingCount, LottoTicketGenerator lottoTicketGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < buyingCount; i++) {
            userAccount.withdraw(LOTTO_PRICE);
            lottoTickets.add(lottoTicketGenerator.generate());
        }
        return lottoTickets;
    }

    public int calculateAvailablePurchases(UserAccount userAccount) {
        return userAccount.getBalance() / LOTTO_PRICE;
    }
}
