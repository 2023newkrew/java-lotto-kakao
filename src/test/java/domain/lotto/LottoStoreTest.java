package domain.lotto;

import lotto.domain.UserAccount;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.store.LottoStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class LottoStoreTest {

    @Test
    @DisplayName("로또를 구매할 수 있다.")
    void buyLottoTicket() {
        UserAccount userAccount = new UserAccount(LottoStore.LOTTO_PRICE);
        LottoStore lottoStore = new LottoStore();
        List<LottoNumber> lottoNumberList = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        assertThatNoException()
                .isThrownBy(() -> lottoStore.buyLottoTicket(userAccount, lottoNumberList));
    }
}
