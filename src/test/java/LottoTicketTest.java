import domain.lotto.ticket.LottoNumber;
import domain.lotto.ticket.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {

    @Test
    @DisplayName("로또 용지를 생성하는 기능")
    void createLottoTicketTest() {
        List<LottoNumber> lottoNumberList =
                List.of(new LottoNumber(6), new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11));

        LottoTicket lottoTicket = new LottoTicket(lottoNumberList);

    }

    @Test
    @DisplayName("로또 번호는 숫자 중복 없음")
    void checkDuplicateLottoTicketTest() {
        List<LottoNumber> lottoNumberList =
                List.of(new LottoNumber(6), new LottoNumber(6), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(lottoNumberList)
                );
    }

    @Test
    @DisplayName("로또 번호는 1~45 만 가능")
    void checkLottoNumberDomainTest() {

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(-1, 2, 3, 4, 5, 6))))
                );

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        new LottoTicket(LottoNumber.numbersToLottoNumbers(new ArrayList<>(List.of(1, 46, 3, 4, 5, 6))))
                );
    }
}
