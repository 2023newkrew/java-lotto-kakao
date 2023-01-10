package lotto.domain.lotto.store.dto;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoNumber;

import java.util.List;

public class LottoTicketBuyRequestDto {

    private final UserAccount userAccount;

    private final List<LottoNumber> lottoNumberList;

    public LottoTicketBuyRequestDto(UserAccount userAccount, List<LottoNumber> lottoNumberList) {
        this.userAccount = userAccount;
        this.lottoNumberList = lottoNumberList;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }
}
