package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성() {
        assertThatCode(() -> new LottoTicket(
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
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        new LottoValue(1),
                        new LottoValue(2),
                        new LottoValue(3),
                        new LottoValue(4),
                        new LottoValue(3),
                        new LottoValue(6)
                ))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수가_6개가_아니면_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(
                        new LottoValue(1),
                        new LottoValue(2),
                        new LottoValue(3),
                        new LottoValue(4),
                        new LottoValue(5)
                ))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toString_에서_정렬된_값_반환() {
        LottoTicket lt = new LottoTicket(Arrays.asList(
                new LottoValue(5),
                new LottoValue(4),
                new LottoValue(3),
                new LottoValue(7),
                new LottoValue(2),
                new LottoValue(9)));
        assertThat(lt.toString()).isEqualTo("[2, 3, 4, 5, 7, 9]");
    }
}
