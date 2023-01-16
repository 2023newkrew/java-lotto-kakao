package domain.lotto;

import lotto.domain.UserAccount;
import lotto.domain.lotto.store.LottoStore;
import lotto.domain.lotto.ticket.generator.LottoTicketRandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class LottoStoreTest {

    @Test
    @DisplayName("로또를 구매할 수 있다.")
    void buyLottoTicket() {
        UserAccount userAccount = new UserAccount(LottoStore.LOTTO_PRICE);
        LottoStore lottoStore = new LottoStore();

        assertThatNoException()
                .isThrownBy(() -> lottoStore.buyLottoTicket(userAccount, 1, new LottoTicketRandomGenerator()));
    }
}
