package domain.lotto;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.ticket.LottoTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void generateLottoTicket() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(lottoNumbers));
    }

    @Test
    @DisplayName("생성된 로또 번호 중 중복이 있으면 예외가 발생한다")
    void generateLottoTicket_throw() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(1), //duplicate
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(lottoNumbers));
    }
}
