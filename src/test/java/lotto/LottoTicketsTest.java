package lotto;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @DisplayName("로또 티켓 번호 일괄 확인")
    @Test
    public void get_lotto_numbers_string() {
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                Arrays.asList(1,2,3,4,5,6),
                Arrays.asList(3,6,15,21,38,41)
        ));
        Assertions.assertThat(lottoTickets.getLottoNumbersString())
                .isEqualTo("[1, 2, 3, 4, 5, 6]\n[3, 6, 15, 21, 38, 41]");
    }
}
