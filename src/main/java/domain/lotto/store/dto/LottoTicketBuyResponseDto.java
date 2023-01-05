package domain.lotto.store.dto;

import domain.UserAccount;
import domain.lotto.ticket.LottoTicket;

public class LottoTicketBuyResponseDto {

    private final UserAccount userAccount;

    private final LottoTicket lottoTicket;

    public LottoTicketBuyResponseDto(UserAccount userAccount, LottoTicket lottoTicket) {
        this.userAccount = userAccount;
        this.lottoTicket = lottoTicket;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
