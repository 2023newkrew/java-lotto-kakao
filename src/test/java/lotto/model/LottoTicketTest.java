package lotto.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketTest {
    @Test
    void 여섯개의_다른_숫자를_가진_티켓_생성() {
        assertThatCode(() -> new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6))).doesNotThrowAnyException();
    }

    @Test
    void 중복된_숫자가_있는_경우_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 3, 6))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 개수가_6개가_아니면_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음수가_있으면_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(-1, 2, 3, 4, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_밖_값이_있는_경우_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(0, 2, 3, 4, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 범위_밖_값이_있는_경우_예외_발생2() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 46, 5))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toString_에서_정렬된_값_반환() {
        LottoTicket lt = new LottoTicket(Arrays.asList(5, 4, 3, 7, 2, 9));
        assertThat(lt.toString()).isEqualTo("[2, 3, 4, 5, 7, 9]");
    }

    @Test
    void 보너스_포함_티켓_생성() {
        assertThatCode(() -> new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6), 7)).doesNotThrowAnyException();
    }

    @Test
    void 보너스_중복시_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 3, 6), 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_범위_벗어날_경우_예외_발생() {
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 3, 6), -1)).isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> new LottoTicket(
                Arrays.asList(1, 2, 3, 4, 3, 6), 47)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 문자열을_통해_티켓_생성() {
        assertThatCode(() -> new LottoTicket("1,2,3,4,5,6", "7"))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoTicket("20,21, 22, 23, 24, 25", "10"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,3,4,5:7",
            ":10",
            "-10, 1,2,3,4,5:10",
            "41,42,43,44,45,46:1"}, delimiter = ':')
    void 문자열을_통해_티켓_생성_실패(String lottoNumbers, String bonusNumber) {
        assertThatCode(() -> new LottoTicket(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
