package domain.lotto.store;

import domain.UserAccount;
import domain.lotto.store.dto.LottoTicketBuyRequestDto;
import domain.lotto.store.dto.LottoTicketBuyResponseDto;
import domain.lotto.ticket.LottoTicket;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public LottoTicketBuyResponseDto buyLottoTicket(LottoTicketBuyRequestDto requestDto) {
        return new LottoTicketBuyResponseDto(
                requestDto.getUserAccount()
                        .withdraw(LOTTO_PRICE),
                new LottoTicket(requestDto.getLottoNumberList())
        );
    }

    public boolean canBuyLotto(UserAccount userAccount) {
        return userAccount.hasEnoughMoney(LOTTO_PRICE);
    }
}
