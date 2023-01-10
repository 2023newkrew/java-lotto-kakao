package lotto.domain.lotto.store;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.ticket.LottoTicket;

import java.util.List;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public LottoTicket buyLottoTicket(UserAccount userAccount, List<LottoNumber> lottoNumbers) {
        userAccount.withdraw(LOTTO_PRICE);
        return new LottoTicket(lottoNumbers);
    }

    public boolean canBuyLotto(UserAccount userAccount) {
        return userAccount.hasEnoughMoney(LOTTO_PRICE);
    }
}
