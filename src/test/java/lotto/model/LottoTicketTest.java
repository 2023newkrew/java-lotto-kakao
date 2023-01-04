package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성시_예외발생없이_생성되어야함() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(6)
                ))).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_있는_경우_예외가_발생되어야함() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(5)
                ))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_개수가_6개가_아니면_예외가_발생되어야함() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5)
                ))).isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        LottoNumber.valueOf(1),
                        LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3),
                        LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5),
                        LottoNumber.valueOf(6),
                        LottoNumber.valueOf(7)
                ))).isInstanceOf(IllegalArgumentException.class);
    }
}