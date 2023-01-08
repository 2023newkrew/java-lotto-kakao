package lotto.domain;

import static lotto.LottoConfig.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class TicketsTest {

    @Test
    void 금액을_제공해서_티켓을_생성하면_금액에_맞는_갯수가_저장된다() {
        Tickets tickets = new Tickets(10000);
        int expected = 10000 / LOTTO_PRICE;
        assertThat(tickets.getCount()).isEqualTo(expected);
    }

    @Test
    void 구매_가능한_갯수보다_더_많이_티켓을_사용하려_하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Tickets(0).use(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
