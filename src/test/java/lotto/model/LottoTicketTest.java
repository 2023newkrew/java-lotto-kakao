package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                ))).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_있는_경우_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(3),
                        new LottoNumber(6)
                ))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수가_6개가_아니면_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)
                ))).isInstanceOf(IllegalArgumentException.class);
    }
}