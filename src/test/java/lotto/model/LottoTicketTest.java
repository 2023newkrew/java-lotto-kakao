package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성시_예외발생없이_생성되어야함() {
        assertThatCode(() -> LottoTicket.fromNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 6))
        ).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_있는_경우_예외가_발생되어야함() {
        assertThatCode(() -> LottoTicket.fromNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자_개수가_6개가_아니면_예외가_발생되어야함() {
        assertThatCode(() -> LottoTicket.fromNumbers(
                Arrays.asList(1, 2, 3, 4, 5))
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatCode(() -> LottoTicket.fromNumbers(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}