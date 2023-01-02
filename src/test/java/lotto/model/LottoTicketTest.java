package lotto.model;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성() {
        assertThatCode(()->new LottoTicket(
                Arrays.asList(
                        new LottoValue(1),
                        new LottoValue(2),
                        new LottoValue(3),
                        new LottoValue(4),
                        new LottoValue(5),
                        new LottoValue(6)
                ))).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_있는_경우_예외_발생() {
        assertThatCode(()->new LottoTicket(
                Arrays.asList(
                        new LottoValue(1),
                        new LottoValue(2),
                        new LottoValue(3),
                        new LottoValue(4),
                        new LottoValue(3),
                        new LottoValue(6)
                ))).isInstanceOf(IllegalArgumentException.class);
    }
}